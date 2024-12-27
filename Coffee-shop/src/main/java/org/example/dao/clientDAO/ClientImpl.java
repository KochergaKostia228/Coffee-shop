package org.example.dao.clientDAO;

import org.example.dao.ConnectionFactory;
import org.example.exception.ConnectionDBException;
import org.example.model.Client;
import org.example.model.MenuItem;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientImpl implements ClientDAO{

    private static final String SAVE_CLIENT = "INSERT INTO Client(first_name, last_name, middle_name, date_of_birth, telephone, address, discount_percent)" +
            "VALUES(?,?,?,?,?,?,?)";

    private static final String FIND_ALL_CLIENT = "SELECT * FROM Client";

    private static final String DELETE_ALL_CLIENT = "DELETE FROM Client";
    private static final String UPDATE_CLIENT = "UPDATE Client SET first_name = ?, last_name = ?, middle_name = ?, date_of_birth = ?, telephone = ?, address = ?, discount_percent = ?" +
            " WHERE Client.id = ? ";
    private static final String DELETE_CLIENT = "DELETE FROM Client WHERE Client.id = ?";

    @Override
    public void save(Client client) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_CLIENT)) {
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getMiddleName());
            ps.setDate(4, client.getDateOfBirth());
            ps.setString(5, client.getTelephone());
            ps.setString(6, client.getAddress());
            ps.setInt(7, client.getDiscountPercent());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void saveMany(List<Client> clients) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_CLIENT)) {

            for (Client currentClient : clients) {
                ps.setString(1, currentClient.getFirstName());
                ps.setString(2, currentClient.getLastName());
                ps.setString(3, currentClient.getMiddleName());
                ps.setDate(4, currentClient.getDateOfBirth());
                ps.setString(5, currentClient.getTelephone());
                ps.setString(6, currentClient.getAddress());
                ps.setInt(7, currentClient.getDiscountPercent());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Client client) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_CLIENT)) {
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getMiddleName());
            ps.setDate(4, client.getDateOfBirth());
            ps.setString(5, client.getTelephone());
            ps.setString(6, client.getAddress());
            ps.setInt(7, client.getDiscountPercent());
            ps.setLong(8, client.getId());
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Client client) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_CLIENT)) {
            ps.setLong(1, client.getId());
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Client> findAll() {
        List<Client> resultClients = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_CLIENT)) {

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                Client addClient = new Client();
                addClient.setId(result.getLong(1));
                addClient.setFirstName(result.getString(2));
                addClient.setLastName(result.getString(3));
                addClient.setMiddleName(result.getString(4));
                addClient.setDateOfBirth(result.getDate(5));
                addClient.setTelephone(result.getString(6));
                addClient.setAddress(result.getString(7));
                addClient.setDiscountPercent(result.getInt(8));

                resultClients.add(addClient);
            }
            return resultClients;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultClients;
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_CLIENT)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
