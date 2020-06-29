package com.example.effort.time;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class TimeServiceImpl implements TimeService {
    private final TimeRepository timeRepo;
    private final CurrentTimeEntryRepository currentRepo;
    private final FinishedTimeEntryRepository finishedRepo;

    public TimeServiceImpl(TimeRepository timeRepo, CurrentTimeEntryRepository currentRepo, FinishedTimeEntryRepository finishedRepo) {
        this.timeRepo = timeRepo;
        this.currentRepo = currentRepo;
        this.finishedRepo = finishedRepo;
    }

    @Override
    public List<TimeEntry> getAllByDate(String date) {
        LocalDate d = LocalDate.parse(date);
        return timeRepo.findByDateOrderByStartTime(d);
    }

    @Override
    public List<CurrentTimeEntry> getCurrent() {
        return currentRepo.findAllForUser();
    }

    @Override
    public TimeEntry insertFinished(FinishedTimeEntry timeEntry) {
        return finishedRepo.save(timeEntry);
    }

    @Override
    public TimeEntry insertCurrent(CurrentTimeEntry timeEntry) {
        return currentRepo.save(timeEntry);
    }

    @Override
    public TimeEntry editFinished(FinishedTimeEntry timeEntry) {
        return finishedRepo.save(timeEntry);
    }

    @Override
    public TimeEntry editCurrent(CurrentTimeEntry timeEntry) {
        return currentRepo.save(timeEntry);
    }

    @Override
    public void deleteFinishedById(Long id) {
        finishedRepo.deleteById(id);
    }

    @Override
    public void deleteCurrentById(Long id) {
        currentRepo.deleteById(id);
    }

    @Override
    public Integer getTotalForDate(String date) {
        LocalDate d = LocalDate.parse(date);
        List<TimeEntry> entries = timeRepo.findByDateOrderByStartTime(d);
        return entries.stream().mapToInt(TimeEntry::getDuration).sum();
    }

    @Override
    public Long getTotalForPeriod(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return timeRepo.getTotalForPeriod(start, end);
    }

    @Override
    public List<DateAndDurationView> getTotalByDateForMonth(String monthDate) {
        int year = Integer.parseInt(monthDate.substring(0, 4));
        int month = Integer.parseInt(monthDate.substring(5));
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1L).minusDays(1L);
        return getTotalByDateForPeriod(startDate, endDate);
    }

    @Override
    public List<DateAndDurationView> getTotalByDateForPeriod(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return getTotalByDateForPeriod(start, end);
    }

    private List<DateAndDurationView> getTotalByDateForPeriod(LocalDate startDate, LocalDate endDate) {
        Map<LocalDate, Long> out = new TreeMap<>();
        long numOfDays = endDate.toEpochDay() - startDate.toEpochDay() + 1;
        List<DateAndDurationView> res = timeRepo.getTotalByDate(startDate, endDate);
        res.forEach(item -> out.put(item.getDate(), item.getTotal()));
        for (int i = 0; i < numOfDays; i++) {
            LocalDate next = startDate.plusDays(i);
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

    private FinishedTimeEntry currentToFinished(CurrentTimeEntry c) {
        return new FinishedTimeEntry(c.getUser(), c.getDate(), c.getCategory(), c.getStartTime(), c.getEndTime());
    }

}
