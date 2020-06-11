package com.example.effort.time;

import java.util.List;

public interface TimeService {

    List<TimeEntry> getAllByDate(String date);
    List<CurrentTimeEntry> getCurrent();
    TimeEntry insertFinished(FinishedTimeEntry timeEntry);
    TimeEntry insertCurrent(CurrentTimeEntry timeEntry);
    TimeEntry editFinished(FinishedTimeEntry timeEntry);
    TimeEntry editCurrent(CurrentTimeEntry timeEntry);
    TimeEntry endCurrent(CurrentTimeEntry timeEntry);
    void deleteById(Long id);
    Integer getTotalForDate(String date);
    Long getTotalForPeriod(String startDate, String endDate);
    List<DateAndDurationView> getTotalByMonth(String date);

}
