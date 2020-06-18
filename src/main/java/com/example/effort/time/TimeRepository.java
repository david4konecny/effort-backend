package com.example.effort.time;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TimeRepository extends JpaRepository<TimeEntry, Long> {

    List<TimeEntry> findByDateOrderByStartTime(LocalDate date);

    @Query("select new com.example.effort.time.DateAndDurationView(t.date, sum(t.endTime - t.startTime)) from TimeEntry t where t.date >= ?1 and t.date <= ?2 group by t.date")
    List<DateAndDurationView> getTotalByDate(LocalDate startDate, LocalDate endDate);

    @Query("select sum(t.endTime - t.startTime) from TimeEntry t where t.date >= ?1 and t.date <= ?2")
    Long getTotalForPeriod(LocalDate startDate, LocalDate endDate);

}
