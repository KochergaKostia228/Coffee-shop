package org.example.dao.menuItemTypeDAO;

import org.example.dao.ConnectionFactory;
import org.example.exception.ConnectionDBException;
import org.example.model.MenuItemType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemTypeImpl implements MenuItemTypeDAO {
    private static final String SAVE_MENU_ITEM_TYPE = "INSERT INTO MenuItemType(name) VALUES(?)";

    private static final String FIND_ALL_MENU_ITEM_TYPE = "SELECT * FROM MenuItemType";
    private static final String FIND_MENU_ITEM_TYPE_BY_ID = "SELECT * FROM MenuItemType WHERE MenuItemType.id = ?";

    private static final String DELETE_ALL_MENU_ITEM_TYPE = "DELETE FROM MenuItemType";
    private static final String UPDATE_MENU_ITEM_TYPE = "UPDATE MenuItemType SET name = ?" +
            " WHERE MenuItemType.id = ? ";
    private static final String DELETE_MENU_ITEM_TYPE = "DELETE FROM MenuItemType WHERE MenuItemType.id = ?";

    @Override
    public void save(MenuItemType menuItemType) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_MENU_ITEM_TYPE)) {
            ps.setString(1, menuItemType.getName());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void saveMany(List<MenuItemType> menuItemTypesList) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_MENU_ITEM_TYPE)) {

            for (MenuItemType currentGroup : menuItemTypesList) {
                ps.setString(1, currentGroup.getName());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(MenuItemType menuItemType) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_MENU_ITEM_TYPE)) {
            ps.setString(1, menuItemType.getName());
            ps.setLong(2, menuItemType.getId());
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(MenuItemType menuItemType) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_MENU_ITEM_TYPE)) {
            ps.setLong(1, menuItemType.getId());
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<MenuItemType> findAll() {
        List<MenuItemType> resultMenuItemType = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_MENU_ITEM_TYPE)) {

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                MenuItemType addMenuItemType = new MenuItemType();
                addMenuItemType.setId(result.getLong(1));
                addMenuItemType.setName(result.getString(2));
                resultMenuItemType.add(addMenuItemType);
            }
            return resultMenuItemType;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultMenuItemType;
    }

    @Override
    public MenuItemType findById(long id) {
        MenuItemType resultMenuItemType = new MenuItemType();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_MENU_ITEM_TYPE_BY_ID)) {
            ps.setLong(1, id);

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                resultMenuItemType.setId(result.getLong(1));
                resultMenuItemType.setName(result.getString(2));
            }

            return resultMenuItemType;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultMenuItemType;
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_MENU_ITEM_TYPE)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
