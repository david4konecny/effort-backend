package com.example.effort.time;

import java.util.List;

public interface TimeService {

    List<TimeSession> getAllByDate(String date);
    void insertAll(List<TimeSession> timeEntries);
    TimeSession insert(TimeSession timeEntry);
    TimeSession edit(TimeSession timeEntry);
    void deleteById(Long id);
    Integer getTotalDuration(String date);

}