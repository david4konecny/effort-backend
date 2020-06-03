package com.example.effort.time;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class TimeController {
    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("/time")
    public List<TimeSession> getAllByDate(@RequestParam String date) {
        return timeService.getAllByDate(date);
    }

    @PostMapping("/time")
    public TimeSession insert(@RequestBody TimeSession timeEntry) {
        return timeService.insert(timeEntry);
    }

    @PutMapping("/time")
    public TimeSession edit(@RequestBody TimeSession timeEntry) {
        return timeService.insert(timeEntry);
    }

    @DeleteMapping("/time/{id}")
    public void delete(@PathVariable Long id) {
        timeService.deleteById(id);
    }

    @GetMapping("/time/total")
    public Integer totalDurationByDate(@RequestParam String date) {
        return timeService.getTotalDuration(date);
    }

    @GetMapping("/time/total/month")
    public List<DateAndDurationView> monthStats(@RequestParam String date) {
        return timeService.getTotalByMonth(date);
    }

}
