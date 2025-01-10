package org.example.dao.orderMenuItemDAO;

import org.example.dao.ConnectionFactory;
import org.example.exception.ConnectionDBException;
import org.example.model.OrderInfo;
import org.example.model.OrderMenuItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderMenuItemImpl implements OrderMenuItemDAO{

    private static final String SAVE_ORDER_MENU_ITEM = "INSERT INTO Orders_MenuItems(order_id, menu_item_id, price)" +
            "VALUES(?,?,?)";

    private static final String FIND_ALL_ORDER_MENU_ITEM = "SELECT * FROM Orders_MenuItems";

    private static final String DELETE_ALL_ORDER_MENU_ITEM = "DELETE FROM Orders_MenuItems";

    private static final String UPDATE_ORDER_MENU_ITEM = "UPDATE Orders_MenuItems SET order_id = ?, menu_item_id = ?, price = ?" +
            " WHERE Orders_MenuItems.id = ? ";
    private static final String DELETE_ORDER_MENU_ITEM = "DELETE FROM Orders_MenuItems WHERE Orders_MenuItems.id = ?";

    @Override
    public void save(OrderMenuItem orderMenuItem) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_ORDER_MENU_ITEM)) {
            ps.setLong(1, orderMenuItem.getOrderId());
            ps.setLong(2, orderMenuItem.getMenuItemId());
            ps.setDouble(3, orderMenuItem.getPrice());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void saveMany(List<OrderMenuItem> listOrderMenuItem) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_ORDER_MENU_ITEM)) {

            for (OrderMenuItem currentOrderMenuItem : listOrderMenuItem) {
                ps.setLong(1, currentOrderMenuItem.getOrderId());
                ps.setLong(2, currentOrderMenuItem.getMenuItemId());
                ps.setDouble(3, currentOrderMenuItem.getPrice());
                ps.execute();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(OrderMenuItem orderMenuItem) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_ORDER_MENU_ITEM)) {
            ps.setLong(1, orderMenuItem.getOrderId());
            ps.setLong(2, orderMenuItem.getMenuItemId());
            ps.setDouble(3, orderMenuItem.getPrice());
            ps.setLong(4, orderMenuItem.getId());
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(OrderMenuItem orderMenuItem) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ORDER_MENU_ITEM)) {
            ps.setLong(1, orderMenuItem.getId());
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<OrderMenuItem> findAll() {
        List<OrderMenuItem> resultOrderMenuItem = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_ORDER_MENU_ITEM)) {

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                OrderMenuItem addOrderMenuItem = new OrderMenuItem();
                addOrderMenuItem.setId(result.getLong(1));
                addOrderMenuItem.setOrderId(result.getLong(2));
                addOrderMenuItem.setMenuItemId(result.getLong(3));
                addOrderMenuItem.setPrice(result.getDouble(4));

                resultOrderMenuItem.add(addOrderMenuItem);
            }
            return resultOrderMenuItem;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultOrderMenuItem;
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_ORDER_MENU_ITEM)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
