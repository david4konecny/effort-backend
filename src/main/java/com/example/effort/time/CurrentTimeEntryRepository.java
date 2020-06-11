package com.example.effort.time;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentTimeEntryRepository extends JpaRepository<CurrentTimeEntry, Long> {
}
