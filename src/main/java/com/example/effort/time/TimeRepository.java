package com.example.effort.time;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TimeRepository extends JpaRepository<TimeSession, Long> {

    List<TimeSession> findByDate(LocalDate date);
    @Query("select t.date, sum(t.endTime - t.startTime) from TimeSession t where t.date >= ?1 and t.date <= ?2 group by t.date")
    List<Object[]> getTotalByDate(LocalDate startDate, LocalDate endDate);

}
