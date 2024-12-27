package org.example.server;

import org.example.dao.ConnectionFactory;
import org.example.dao.menuItemTypeDAO.MenuItemTypeDAO;
import org.example.dao.menuItemTypeDAO.MenuItemTypeImpl;
import org.example.dao.personnelPositionDAO.PersonnelPositionDAO;
import org.example.dao.personnelPositionDAO.PersonnelPositionImpl;
import org.example.exception.ConnectionDBException;
import org.example.exception.FileException;
import org.example.model.MenuItemType;
import org.example.model.PersonnelPosition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoffeeShopDBInitializer {
    private static final Random RANDOM_GENERATOR = new Random();
    private static final List<String> TABLES_NAME_ARRAY;
    private static final String SQL_SCRIPT_CREATE_TABLES;

    static {
        SQL_SCRIPT_CREATE_TABLES = PropertyFactory.getInstance().getProperty().getProperty("db.sqlScriptCreateTables");

        String tablesNames = PropertyFactory.getInstance().getProperty().getProperty("db.tablesNames");
        TABLES_NAME_ARRAY = Arrays.stream(tablesNames.split(",")).collect(Collectors.toList());
    }

    public static void createTables()
    {
        try {
            Connection connection = ConnectionFactory.getInstance().makeConnection();

            for (String tableName : TABLES_NAME_ARRAY){
                if(!tableExists(tableName)){
                    try(Stream<String> lineStream = Files.lines(Paths.get(SQL_SCRIPT_CREATE_TABLES)))
                    {
                        StringBuilder createTableQuery = new StringBuilder();

                        for (String  currentString : lineStream.collect(Collectors.toList())){
                            createTableQuery.append(currentString).append(" ");
                        }
                        try(PreparedStatement ps = connection.prepareStatement(createTableQuery.toString()))
                        {
                            ps.execute();
                        }
                    }
                    catch (IOException ex){
                        throw new FileException("Error with createTables.sql");
                    }
                    catch (SQLException ex){
                        System.err.println(ex.getMessage());
                    }
                }
            }
        }
        catch (ConnectionDBException | FileException ex){
            System.err.println(ex.getMessage());
        }
    }

    public static void createMenuItemType() throws FileException {
        String coursesFileName = PropertyFactory.getInstance().getProperty().getProperty("data.menuitemtype");
        MenuItemTypeDAO menuItemTypeDAO = new MenuItemTypeImpl();

        List<MenuItemType> menuItemTypeList = new ArrayList<>();
        try (Stream<String> lineStream = Files.lines(Paths.get(coursesFileName))) {
            for (var currentString : lineStream.collect(Collectors.toList())) {
                MenuItemType menuItemType = new MenuItemType();
                menuItemType.setName(currentString);

                menuItemTypeList.add(menuItemType);
            }

            if(menuItemTypeDAO.findAll().size() == 0){
                menuItemTypeDAO.saveMany(menuItemTypeList);
            }
        } catch (IOException exception) {
            throw new FileException("Error with createTables.sql");
        }
    }

    public static void createPersonnelPosition() throws FileException {
        String coursesFileName = PropertyFactory.getInstance().getProperty().getProperty("data.personnelposition");
        PersonnelPositionDAO personnelPositionDAO = new PersonnelPositionImpl();

        List<PersonnelPosition> personnelPositionList = new ArrayList<>();
        try (Stream<String> lineStream = Files.lines(Paths.get(coursesFileName))) {
            for (var currentString : lineStream.collect(Collectors.toList())) {
                PersonnelPosition personnelPosition = new PersonnelPosition();
                personnelPosition.setName(currentString);

                personnelPositionList.add(personnelPosition);
            }

            if(personnelPositionDAO.findAll().size() == 0){
                personnelPositionDAO.saveMany(personnelPositionList);
            }
        } catch (IOException exception) {
            throw new FileException("Error with createTables.sql");
        }
    }

    private static boolean tableExists(String tableName) throws ConnectionDBException {
        try (Connection connection = ConnectionFactory.getInstance().makeConnection()){
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet resultSet = meta.getTables(null, null, tableName, new String[]{"TABLE"});
            return resultSet.next();
        } catch (SQLException exception) {
            throw new ConnectionDBException("error connection to DB");
        }
    }
}
