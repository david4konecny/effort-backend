package com.example.effort.time;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public List<TimeEntry> getAllByDate(String date) {
        LocalDate d = LocalDate.parse(date);
        return timeRepository.findByDate(d);
    }

    @Override
    public void insertAll(List<TimeEntry> timeEntries) {
        timeRepository.saveAll(timeEntries);
    }

    @Override
    public TimeEntry insert(TimeEntry timeEntry) {
        return timeRepository.save(timeEntry);
    }

    @Override
    public TimeEntry edit(TimeEntry timeEntry) {
        return timeRepository.save(timeEntry);
    }

    @Override
    public void deleteById(Long id) {
        timeRepository.deleteById(id);
    }

    @Override
    public Integer getTotalForDate(String date) {
        LocalDate d = LocalDate.parse(date);
        List<TimeEntry> entries = timeRepository.findByDate(d);
        return entries.stream().mapToInt(TimeEntry::getDuration).sum();
    }

    @Override
    public Long getTotalForPeriod(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return timeRepository.getTotalForPeriod(start, end);
    }

    @Override
    public List<DateAndDurationView> getTotalByMonth(String date) {
        Map<LocalDate, Long> out = new TreeMap<>();
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5));
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1L).minusDays(1L);
        List<DateAndDurationView> res = timeRepository.getTotalByDate(startDate, endDate);
        res.forEach(item -> out.put(item.getDate(), item.getTotal()));
        for (int i = 1; i <= endDate.getDayOfMonth(); i++) {
            LocalDate next = LocalDate.of(year, month, i);
            if (!out.containsKey(next)) {
                out.put(next, 0L);
            }
        }
        List<DateAndDurationView> list = new ArrayList<>();
        for (Map.Entry<LocalDate, Long> entry : out.entrySet()) {
            list.add(new DateAndDurationView(entry.getKey(), entry.getValue()));
        }
        return list;
    }

}
