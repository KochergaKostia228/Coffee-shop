package org.example.dao.workScheduleDAO;

import org.example.dao.ConnectionFactory;
import org.example.exception.ConnectionDBException;
import org.example.model.PersonnelPosition;
import org.example.model.WorkSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkScheduleImpl implements WorkScheduleDAO{

    private static final String SAVE_WORK_SCHEDULE = "INSERT INTO Work_schedule(personnel_id, start_time_work, end_time_work, dayweek)" +
            "VALUES(?,?,?,?)";

    private static final String FIND_ALL_WORK_SCHEDULE = "SELECT * FROM Work_schedule";

    private static final String DELETE_ALL_WORK_SCHEDULE = "DELETE FROM Work_schedule";
    private static final String UPDATE_WORK_SCHEDULE = "UPDATE Work_schedule SET personnel_id = ?, start_time_work = ?, end_time_work = ?, dayweek = ?" +
            " WHERE Work_schedule.id = ? ";
    private static final String DELETE_WORK_SCHEDULE = "DELETE FROM Work_schedule WHERE Work_schedule.id = ?";

    @Override
    public void save(WorkSchedule workSchedule) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_WORK_SCHEDULE)) {
            ps.setLong(1, workSchedule.getPersonnelId());
            ps.setTime(2, workSchedule.getStartTimeWork());
            ps.setTime(3, workSchedule.getEndTimeWork());
            ps.setString(4, workSchedule.getDayWeek().name());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void saveMany(List<WorkSchedule> items) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_WORK_SCHEDULE)) {

            for (WorkSchedule currentWorkSchedule : items) {
                ps.setLong(1, currentWorkSchedule.getPersonnelId());
                ps.setObject(2, currentWorkSchedule.getStartTimeWork());
                ps.setObject(3, currentWorkSchedule.getEndTimeWork());
                ps.setString(4, currentWorkSchedule.getDayWeek().name());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(WorkSchedule workSchedule) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_WORK_SCHEDULE)) {
            ps.setLong(1, workSchedule.getPersonnelId());
            ps.setObject(2, workSchedule.getStartTimeWork());
            ps.setObject(3, workSchedule.getEndTimeWork());
            ps.setString(4, workSchedule.getDayWeek().name());
            ps.setLong(5, workSchedule.getId());
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(WorkSchedule workSchedule) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_WORK_SCHEDULE)) {
            ps.setLong(1, workSchedule.getId());
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<WorkSchedule> findAll() {
        List<WorkSchedule> workSchedules = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_WORK_SCHEDULE)) {

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                WorkSchedule addWorkSchedule = new WorkSchedule();
                addWorkSchedule.setId(result.getLong(1));
                addWorkSchedule.setPersonnelId(result.getLong(2));
                addWorkSchedule.setStartTimeWork(result.getTime(3));
                addWorkSchedule.setEndTimeWork(result.getTime(4));
                addWorkSchedule.setPersonnelId(result.getLong(5));
                workSchedules.add(addWorkSchedule);
            }
            return workSchedules;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return workSchedules;
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_WORK_SCHEDULE)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
