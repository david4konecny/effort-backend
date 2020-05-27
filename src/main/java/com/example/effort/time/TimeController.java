package com.example.effort.time;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
