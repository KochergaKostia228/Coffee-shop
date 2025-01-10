package org.example.dao.menuItemDAO;

import org.example.dao.ConnectionFactory;
import org.example.dao.menuItemTypeDAO.MenuItemTypeDAO;
import org.example.exception.ConnectionDBException;
import org.example.model.MenuItem;
import org.example.model.MenuItemType;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemImpl implements MenuItemDAO {
    private static final String SAVE_MENU_ITEM = "INSERT INTO MenuItem(type_id, name_ukraine_language, name_english_language, price)" +
            "VALUES(?,?,?,?)";
   
    private static final String FIND_ALL_MENU_ITEM = "SELECT * FROM MenuItem";
    private static final String FIND_MENU_ITEM_BY_ID = "SELECT * FROM MenuItem WHERE MenuItem.id = ?";

    private static final String DELETE_ALL_MENU_ITEM = "DELETE FROM MenuItem";
    private static final String UPDATE_MENU_ITEM = "UPDATE MenuItem SET type_id = ?, name_ukraine_language = ?, name_english_language = ?, price = ?" +
            " WHERE MenuItem.id = ? ";
    private static final String DELETE_MENU_ITEM = "DELETE FROM MenuItem WHERE MenuItem.id = ?";

    @Override
    public void save(MenuItem menuItem) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_MENU_ITEM)) {
            ps.setLong(1, menuItem.getTypeId());
            ps.setString(2, menuItem.getName_ukraine_language());
            ps.setString(3, menuItem.getName_english_language());
            ps.setBigDecimal(4, new BigDecimal(menuItem.getPrice()));
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void saveMany(List<MenuItem> menuItemList) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_MENU_ITEM)) {

            for (MenuItem currentMenuItem : menuItemList) {
                ps.setLong(1, currentMenuItem.getTypeId());
                ps.setString(2, currentMenuItem.getName_ukraine_language());
                ps.setString(3, currentMenuItem.getName_english_language());
                ps.setBigDecimal(4, new BigDecimal(currentMenuItem.getPrice()));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(MenuItem menuItem) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_MENU_ITEM)) {
            ps.setLong(1, menuItem.getTypeId());
            ps.setString(2, menuItem.getName_ukraine_language());
            ps.setString(3, menuItem.getName_english_language());
            ps.setBigDecimal(4, new BigDecimal(menuItem.getPrice()));
            ps.setLong(5, menuItem.getId());
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(MenuItem menuItem) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_MENU_ITEM)) {
            ps.setLong(1, menuItem.getId());
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<MenuItem> findAll() {
        List<MenuItem> resultMenuItem = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_MENU_ITEM)) {

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                MenuItem addMenuItem = new MenuItem();
                addMenuItem.setId(result.getLong(1));
                addMenuItem.setTypeId(result.getLong(2));
                addMenuItem.setName_ukraine_language(result.getString(3));
                addMenuItem.setName_english_language(result.getString(4));
                addMenuItem.setPrice(result.getDouble(5));

                resultMenuItem.add(addMenuItem);
            }
            return resultMenuItem;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultMenuItem;
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_MENU_ITEM)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public MenuItem findById(long id) {
        MenuItem resultMenuItem = new MenuItem();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_MENU_ITEM_BY_ID)) {

            ps.setLong(1, id);

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                resultMenuItem.setId(result.getLong(1));
                resultMenuItem.setTypeId(result.getLong(2));
                resultMenuItem.setName_ukraine_language(result.getString(3));
                resultMenuItem.setName_english_language(result.getString(4));
                resultMenuItem.setPrice(result.getDouble(5));
            }
            return resultMenuItem;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultMenuItem;
    }
}

