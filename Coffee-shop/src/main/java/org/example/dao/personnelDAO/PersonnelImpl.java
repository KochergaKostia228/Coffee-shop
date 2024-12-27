package org.example.dao.personnelDAO;

import org.example.dao.ConnectionFactory;
import org.example.exception.ConnectionDBException;
import org.example.model.Client;
import org.example.model.Personnel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonnelImpl implements PersonnelDAO{

    private static final String SAVE_PERSONNEL = "INSERT INTO Personnel(first_name, last_name, middle_name, telephone, address, position_id)" +
            "VALUES(?,?,?,?,?,?)";

    private static final String FIND_ALL_PERSONNEL = "SELECT * FROM Personnel";

    private static final String DELETE_ALL_PERSONNEL = "DELETE FROM Personnel";
    private static final String UPDATE_PERSONNEL = "UPDATE Personnel SET first_name = ?, last_name = ?, middle_name = ?, telephone = ?, address = ?, position_id = ?" +
            " WHERE Personnel.id = ? ";
    private static final String DELETE_PERSONNEL = "DELETE FROM Personnel WHERE Personnel.id = ?";


    @Override
    public void save(Personnel personnel) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_PERSONNEL)) {
            ps.setString(1, personnel.getFirstName());
            ps.setString(2, personnel.getLastName());
            ps.setString(3, personnel.getMiddleName());
            ps.setString(4, personnel.getTelephone());
            ps.setString(5, personnel.getAddress());
            ps.setLong(6, personnel.getPositionId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void saveMany(List<Personnel> personnelList) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_PERSONNEL)) {

            for (Personnel currentPersonnel : personnelList) {
                ps.setString(1, currentPersonnel.getFirstName());
                ps.setString(2, currentPersonnel.getLastName());
                ps.setString(3, currentPersonnel.getMiddleName());
                ps.setString(4, currentPersonnel.getTelephone());
                ps.setString(5, currentPersonnel.getAddress());
                ps.setLong(6, currentPersonnel.getPositionId());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Personnel personnel) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_PERSONNEL)) {
            ps.setString(1, personnel.getFirstName());
            ps.setString(2, personnel.getLastName());
            ps.setString(3, personnel.getMiddleName());
            ps.setString(4, personnel.getTelephone());
            ps.setString(5, personnel.getAddress());
            ps.setLong(6, personnel.getPositionId());
            ps.setLong(7, personnel.getId());
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Personnel personnel) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_PERSONNEL)) {
            ps.setLong(1, personnel.getId());
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Personnel> findAll() {
        List<Personnel> resultPersonnelList = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_PERSONNEL)) {

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                Personnel addPersonnel = new Personnel();
                addPersonnel.setId(result.getLong(1));
                addPersonnel.setFirstName(result.getString(2));
                addPersonnel.setLastName(result.getString(3));
                addPersonnel.setMiddleName(result.getString(4));
                addPersonnel.setTelephone(result.getString(5));
                addPersonnel.setAddress(result.getString(6));
                addPersonnel.setPositionId(result.getLong(7));

                resultPersonnelList.add(addPersonnel);
            }
            return resultPersonnelList;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultPersonnelList;
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_PERSONNEL)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
