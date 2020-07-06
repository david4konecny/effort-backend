package com.example.effort.time;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CurrentTimeEntryRepository extends JpaRepository<CurrentTimeEntry, Long> {

    @Query("select c from CurrentTimeEntry c where c.user.id = ?#{ principal?.id }")
    List<CurrentTimeEntry> findAllForUser();

    @Modifying
    @Transactional
    @Query("update CurrentTimeEntry c set c.date = ?#{ [0].date }, c.category = ?#{ [0].category }, c.startTime = ?#{ [0].startTime }, c.endTime = ?#{ [0].endTime } where c.id = ?#{ [0].id } and c.user.id = ?#{ principal?.id }")
    int editEntry(CurrentTimeEntry timeEntry);

    @Modifying
    @Transactional
    @Query("delete from CurrentTimeEntry c where c.id = ?1 and c.user.id = ?#{ principal?.id }")
    int deleteEntry(Long id);

}
