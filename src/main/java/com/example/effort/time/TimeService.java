package com.example.effort.time;

import java.util.List;

public interface TimeService {

    List<TimeEntry> getAllByDate(String date);
    List<CurrentTimeEntry> getCurrent();
    TimeEntry insertFinished(FinishedTimeEntry timeEntry);
    TimeEntry insertCurrent(CurrentTimeEntry timeEntry);
    TimeEntry editFinished(FinishedTimeEntry timeEntry);
    TimeEntry editCurrent(CurrentTimeEntry timeEntry);
    void deleteFinishedById(Long id);
    void deleteCurrentById(Long id);
    Integer getTotalForDate(String date);
    Long getTotalForPeriod(String startDate, String endDate);
    List<DateAndDurationView> getTotalByDateForMonth(String monthDate);
    List<DateAndDurationView> getTotalByDateForPeriod(String startDate, String endDate);

}
