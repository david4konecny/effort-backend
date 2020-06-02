package com.example.effort.time;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimeServiceImpl implements TimeService {
    private final TimeRepository timeRepository;

    public TimeServiceImpl(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @Override
    public List<TimeSession> getAllByDate(String date) {
        LocalDate d = LocalDate.parse(date);
        return timeRepository.findByDate(d);
    }

    @Override
    public void insertAll(List<TimeSession> timeEntries) {
        timeRepository.saveAll(timeEntries);
    }

    @Override
    public TimeSession insert(TimeSession timeEntry) {
        return timeRepository.save(timeEntry);
    }

    @Override
    public TimeSession edit(TimeSession timeEntry) {
        return timeRepository.save(timeEntry);
    }

    @Override
    public void deleteById(Long id) {
        timeRepository.deleteById(id);
    }

    @Override
    public Integer getTotalDuration(String date) {
        LocalDate d = LocalDate.parse(date);
        List<TimeSession> entries = timeRepository.findByDate(d);
        return entries.stream().mapToInt(TimeSession::getDuration).sum();
    }
}
