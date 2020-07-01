package com.example.effort.time;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface FinishedTimeEntryRepository extends JpaRepository<FinishedTimeEntry, Long> {

    @Query("select new com.example.effort.time.DateAndDurationView(f.date, sum(f.endTime - f.startTime)) from FinishedTimeEntry f where f.user.id = ?#{ principal?.id } and f.date = ?1")
    DateAndDurationView getTotalForDate(LocalDate date);

}
