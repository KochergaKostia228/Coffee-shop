package org.example.dao.orderInfoDAO;

import org.example.dao.ConnectionFactory;
import org.example.exception.ConnectionDBException;
import org.example.model.MenuItem;
import org.example.model.OrderInfo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderInfoImpl implements OrderInfoDAO{

    private static final String SAVE_ORDER_INFO = "INSERT INTO OrdersInfo(client_id, price_with_discount, date)" +
            "VALUES(?,?,?)";

    private static final String FIND_ALL_ORDER_INFO = "SELECT * FROM OrdersInfo";

    private static final String DELETE_ALL_ORDER_INFO = "DELETE FROM OrdersInfo";
    private static final String UPDATE_ORDER_INFO = "UPDATE OrdersInfo SET client_id = ?, price_with_discount = ?, date = ?" +
            " WHERE OrdersInfo.id = ? ";
    private static final String DELETE_ORDER_INFO = "DELETE FROM OrdersInfo WHERE OrdersInfo.id = ?";

    @Override
    public void save(OrderInfo orderInfo) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_ORDER_INFO)) {
            ps.setLong(1, orderInfo.getClientId());
            ps.setDouble(2, orderInfo.getPriceWithDiscount());
            ps.setDate(3, orderInfo.getDate());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void saveMany(List<OrderInfo> listOrderInfo) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_ORDER_INFO)) {

            for (OrderInfo currentOrderInfo : listOrderInfo) {
                ps.setLong(1, currentOrderInfo.getClientId());
                ps.setDouble(2, currentOrderInfo.getPriceWithDiscount());
                ps.setDate(3, currentOrderInfo.getDate());
                ps.execute();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(OrderInfo orderInfo) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_ORDER_INFO)) {
            ps.setLong(1, orderInfo.getClientId());
            ps.setDouble(2, orderInfo.getPriceWithDiscount());
            ps.setDate(3, orderInfo.getDate());
            ps.setLong(4, orderInfo.getId());
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(OrderInfo orderInfo) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ORDER_INFO)) {
            ps.setLong(1, orderInfo.getId());
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<OrderInfo> findAll() {
        List<OrderInfo> resultOrderInfo = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_ORDER_INFO)) {

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                OrderInfo addMeOrderInfo = new OrderInfo();
                ps.setLong(1, addMeOrderInfo.getId());
                ps.setLong(2, addMeOrderInfo.getClientId());
                ps.setDouble(3, addMeOrderInfo.getPriceWithDiscount());
                ps.setDate(4, addMeOrderInfo.getDate());
                ps.execute();

                resultOrderInfo.add(addMeOrderInfo);
            }
            return resultOrderInfo;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultOrderInfo;
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_ORDER_INFO)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
