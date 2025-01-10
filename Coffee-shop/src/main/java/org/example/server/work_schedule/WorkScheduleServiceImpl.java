package org.example.server.work_schedule;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dao.orderInfoDAO.OrderInfoDAO;
import org.example.dao.orderInfoDAO.OrderInfoImpl;
import org.example.dao.personnelDAO.PersonnelDAO;
import org.example.dao.personnelDAO.PersonnelImpl;
import org.example.dao.personnelPositionDAO.PersonnelPositionDAO;
import org.example.dao.personnelPositionDAO.PersonnelPositionImpl;
import org.example.dao.workScheduleDAO.WorkScheduleDAO;
import org.example.dao.workScheduleDAO.WorkScheduleImpl;
import org.example.model.OrderInfo;
import org.example.model.Personnel;
import org.example.model.PersonnelPosition;
import org.example.model.WorkSchedule;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;


@Data
@NoArgsConstructor
public class WorkScheduleServiceImpl implements WorkScheduleService {


    @Override
    public Optional<WorkSchedule> addWorkScheduleSpecialDay(int personnelIndex, int dayOfWeekIndex, Time startWorkTime, Time endWorkTime) {
        PersonnelDAO personnelDAO = new PersonnelImpl();
        WorkScheduleDAO workScheduleDAO = new WorkScheduleImpl();
        List<Personnel> personnelList = personnelDAO.findAll();
        try {
            Personnel personnel1 = personnelList.get(personnelIndex-1);
            WorkSchedule addWorkSchedule = new WorkSchedule();
            addWorkSchedule.setPersonnelId(personnel1.getId());
            addWorkSchedule.setDayWeek(DayOfWeek.of(dayOfWeekIndex));
            addWorkSchedule.setStartTimeWork(startWorkTime);
            addWorkSchedule.setEndTimeWork(endWorkTime);
            workScheduleDAO.save(addWorkSchedule);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }

        List<WorkSchedule> workScheduleList = workScheduleDAO.findAll();

        for (var workSchedule : workScheduleList) {
            if (workSchedule.getDayWeek().equals(DayOfWeek.of(dayOfWeekIndex))
            ) {
                return Optional.of(workSchedule);
            }
        }
        return Optional.empty();
    }

    @Override
    public void updateWorkSchedule(int workScheduleIndex, int personnelIndex, WorkSchedule updateWorkSchedule) {
        PersonnelDAO personnelDAO = new PersonnelImpl();
        WorkScheduleDAO workScheduleDAO = new WorkScheduleImpl();
        List<Personnel> personnelList = personnelDAO.findAll();
        List<WorkSchedule> workScheduleList = workScheduleDAO.findAll();
        try {
            Personnel personnel1 = personnelList.get(personnelIndex-1);
            WorkSchedule workSchedule = workScheduleList.get(workScheduleIndex-1);
            workSchedule.setPersonnelId(personnel1.getId());
            workSchedule.setDayWeek(updateWorkSchedule.getDayWeek());
            workSchedule.setStartTimeWork(updateWorkSchedule.getStartTimeWork());
            workSchedule.setEndTimeWork(updateWorkSchedule.getEndTimeWork());
            workScheduleDAO.update(workSchedule);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteWorkScheduleByIndex(int workScheduleIndex) {
        WorkScheduleDAO workScheduleDAO = new WorkScheduleImpl();
        List<WorkSchedule> workScheduleList = workScheduleDAO.findAll();
        try {
            WorkSchedule workSchedule = workScheduleList.get(workScheduleIndex-1);
            workScheduleDAO.delete(workSchedule);

        } catch(IndexOutOfBoundsException e) {
            System.err.println("Invalid name of group");
            throw new RuntimeException();
        }
    }
}
