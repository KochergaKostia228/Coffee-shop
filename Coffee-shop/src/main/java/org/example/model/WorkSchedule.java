package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkSchedule {
    private long id;
    private long personnelId;
    private Time startTimeWork;
    private Time endTimeWork;
    private DayOfWeek dayWeek;
}
