package com.example.effort.time;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TimeService {

    List<TimeSession> getAllByDate(String date);
    void insertAll(List<TimeSession> timeEntries);
    TimeSession insert(TimeSession timeEntry);
    TimeSession edit(TimeSession timeEntry);
    void deleteById(Long id);
    Integer getTotalDuration(String date);
    Map<LocalDate, Long> getTotalByMonth(String date);

}
