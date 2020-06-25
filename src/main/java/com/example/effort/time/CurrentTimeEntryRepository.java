package com.example.effort.time;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurrentTimeEntryRepository extends JpaRepository<CurrentTimeEntry, Long> {

    @Query("select c from CurrentTimeEntry c where c.user.id = ?#{ principal?.id }")
    List<CurrentTimeEntry> findAllForUser();

}
