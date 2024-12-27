package org.example.dao.personnelPositionDAO;

import org.example.dao.ConnectionFactory;
import org.example.exception.ConnectionDBException;
import org.example.model.PersonnelPosition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonnelPositionImpl implements PersonnelPositionDAO {
    private static final String SAVE_PERSONNEL_POSITION = "INSERT INTO PersonnelPosition(name) VALUES(?)";

    private static final String FIND_ALL_PERSONNEL_POSITION = "SELECT * FROM PersonnelPosition";
    private static final String FIND_PERSONNEL_POSITION_BY_ID = "SELECT * FROM PersonnelPosition WHERE PersonnelPosition.id = ?";

    private static final String DELETE_ALL_PERSONNEL_POSITION = "DELETE FROM PersonnelPosition";
    private static final String UPDATE_PERSONNEL_POSITION = "UPDATE PersonnelPosition SET name = ?" +
            " WHERE PersonnelPosition.id = ? ";
    private static final String DELETE_PERSONNEL_POSITION = "DELETE FROM PersonnelPosition WHERE PersonnelPosition.id = ?";

    @Override
    public void save(PersonnelPosition personnelPosition) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_PERSONNEL_POSITION)) {
            ps.setString(1, personnelPosition.getName());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void saveMany(List<PersonnelPosition> personnelPositionList) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_PERSONNEL_POSITION)) {

            for (PersonnelPosition currentPersonnelPosition : personnelPositionList) {
                ps.setString(1, currentPersonnelPosition.getName());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(PersonnelPosition personnelPosition) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_PERSONNEL_POSITION)) {
            ps.setString(1, personnelPosition.getName());
            ps.setLong(2, personnelPosition.getId());
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(PersonnelPosition personnelPosition) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_PERSONNEL_POSITION)) {
            ps.setLong(1, personnelPosition.getId());
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<PersonnelPosition> findAll() {
        List<PersonnelPosition> resultMenuItemType = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_PERSONNEL_POSITION)) {

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                PersonnelPosition addMenuItemType = new PersonnelPosition();
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
    public PersonnelPosition findById(long id) {
        PersonnelPosition resultPersonnelPosition = new PersonnelPosition();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_PERSONNEL_POSITION_BY_ID)) {
            ps.setLong(1, id);

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                resultPersonnelPosition.setId(result.getLong(1));
                resultPersonnelPosition.setName(result.getString(2));
            }
            return resultPersonnelPosition;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultPersonnelPosition;
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_PERSONNEL_POSITION)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
