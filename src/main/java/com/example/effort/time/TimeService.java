package com.example.effort.time;

import java.util.List;

public interface TimeService {

    List<TimeEntry> getAllByDate(String date);
    void insertAll(List<TimeEntry> timeEntries);
    TimeEntry insert(TimeEntry timeEntry);
    TimeEntry edit(TimeEntry timeEntry);
    void deleteById(Long id);
    Integer getTotalForDate(String date);
    Long getTotalForPeriod(String startDate, String endDate);
    List<DateAndDurationView> getTotalByMonth(String date);

}
