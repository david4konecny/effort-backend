package com.example.effort.time;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface FinishedTimeEntryRepository extends JpaRepository<FinishedTimeEntry, Long> {

    @Query("select coalesce(sum(f.endTime - f.startTime), 0) from FinishedTimeEntry f where f.user.id = ?#{ principal?.id } and f.date = ?1")
    Long getTotalForDate(LocalDate date);

}
