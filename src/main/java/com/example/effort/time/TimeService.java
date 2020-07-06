package com.example.effort.time;

import java.util.List;

public interface TimeService {

    List<TimeEntry> getAllByDate(String date);
    List<FinishedTimeEntry> getFinishedByDate(String date);
    List<CurrentTimeEntry> getCurrent();
    TimeEntry insertFinished(FinishedTimeEntry timeEntry);
    TimeEntry insertCurrent(CurrentTimeEntry timeEntry);
    int editFinished(FinishedTimeEntry timeEntry);
    int editCurrent(CurrentTimeEntry timeEntry);
    int deleteFinishedById(Long id);
    int deleteCurrentById(Long id);
    Integer getTotalForDate(String date);
    Long getTotalFinishedForDate(String date);
    Long getTotalForPeriod(String startDate, String endDate);
    List<DateAndDurationView> getTotalByDateForMonth(String monthDate);
    List<DateAndDurationView> getTotalByDateForPeriod(String startDate, String endDate);

}
