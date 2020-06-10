package com.example.effort.time;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/time")
public class TimeController {
    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("")
    public List<TimeSession> getAllByDate(@RequestParam String date) {
        return timeService.getAllByDate(date);
    }

    @PostMapping("")
    public TimeSession insert(@RequestBody TimeSession timeEntry) {
        return timeService.insert(timeEntry);
    }

    @PutMapping("")
    public TimeSession edit(@RequestBody TimeSession timeEntry) {
        return timeService.insert(timeEntry);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        timeService.deleteById(id);
    }

    @GetMapping("/total")
    public Integer totalDurationByDate(@RequestParam String date) {
        return timeService.getTotalForDate(date);
    }

    @GetMapping("/total/between")
    public Long totalForPeriod(@RequestParam String startDate, @RequestParam String endDate) {
        return timeService.getTotalForPeriod(startDate, endDate);
    }

    @GetMapping("/total/month")
    public List<DateAndDurationView> monthStats(@RequestParam String date) {
        return timeService.getTotalByMonth(date);
    }

}
