package com.example.effort.time;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    @Override
    public Map<LocalDate, Long> getTotalByMonth(String date) {
        Map<LocalDate, Long> out = new TreeMap<>();
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5));
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1L).minusDays(1L);
        List<Object[]> res = timeRepository.getTotalByDate(startDate, endDate);
        res.forEach(item -> out.put((LocalDate) item[0], (Long) item[1]));
        for (int i = 1; i <= endDate.getDayOfMonth(); i++) {
            LocalDate next = LocalDate.of(year, month, i);
            if (!out.containsKey(next)) {
                out.put(next, 0L);
            }
        }
        return out;
    }

}
