package com.example.effort.time;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface TimeRepository extends JpaRepository<TimeEntry, Long> {

    @Query("select t from TimeEntry t where t.date = ?1 and t.user.id = ?#{ principal?.id } order by t.startTime")
    List<TimeEntry> findByDateOrderByStartTime(LocalDate date);

    @Query("select new com.example.effort.time.DateAndDurationView(t.date, sum(t.endTime - t.startTime)) from TimeEntry t where t.user.id = ?#{ principal?.id } and t.date >= ?1 and t.date <= ?2 group by t.date")
    List<DateAndDurationView> getTotalByDate(LocalDate startDate, LocalDate endDate);

    @Query("select coalesce(sum(t.endTime - t.startTime), 0) from TimeEntry t where t.user.id = ?#{ principal?.id } and t.date >= ?1 and t.date <= ?2")
    Long getTotalForPeriod(LocalDate startDate, LocalDate endDate);

    @Modifying
    @Transactional
    @Query("delete from TimeEntry t where t.user.id = ?#{ principal?.id }")
    void deleteEntries();

    @Modifying
    @Transactional
    @Query("delete from TimeEntry t where t.category.id = ?1 and t.user.id = ?#{ principal?.id }")
    void deleteEntriesForCategory(Long categoryId);

}
