package org.example.server.work_schedule;

import org.example.model.Personnel;
import org.example.model.WorkSchedule;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

public interface WorkScheduleService {
    Optional<WorkSchedule> addWorkScheduleSpecialDay(int personnelIndex, int dayOfWeekIndex, Time startWorkTime, Time endWorkTime);

    void updateWorkSchedule(int workScheduleIndex, int personnelIndex, WorkSchedule updateWorkSchedule);

    void deleteWorkScheduleByIndex(int workScheduleIndex);
}
