package com.example.effort.time;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimeRepository extends JpaRepository<TimeSession, Long> {

    List<TimeSession> findByDate(LocalDate date);

}
