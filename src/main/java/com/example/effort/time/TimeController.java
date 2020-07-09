package com.example.effort.time;

import com.example.effort.user.User;
import com.example.effort.util.exceptions.DataNotValidException;
import com.example.effort.util.exceptions.UpdateFailedException;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/time")
public class TimeController {
    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("")
    public List<TimeEntry> getAllByDate(@RequestParam String date) {
        return timeService.getAllByDate(date);
    }

    @GetMapping("/current")
    public List<CurrentTimeEntry> getCurrent() {
        return timeService.getCurrent();
    }

    @PostMapping("/current")
    public TimeEntry insertCurrent(@RequestBody @Valid CurrentTimeEntry timeEntry, Errors errors, Authentication authentication) {
        if (errors.hasErrors())
            throw new DataNotValidException(errors.getAllErrors());
        User user = (User) authentication.getPrincipal();
        timeEntry.setUser(user);
        return timeService.insertCurrent(timeEntry);
    }

    @GetMapping("/finished")
    public List<FinishedTimeEntry> getFinishedByDate(@RequestParam String date) {
        return timeService.getFinishedByDate(date);
    }

    @PostMapping("/finished")
    public TimeEntry insertFinished(@RequestBody @Valid FinishedTimeEntry timeEntry, Errors errors, Authentication authentication) {
        if (errors.hasErrors())
            throw new DataNotValidException(errors.getAllErrors());
        User user = (User) authentication.getPrincipal();
        timeEntry.setUser(user);
        return timeService.insertFinished(timeEntry);
    }

    @PutMapping("/current")
    public void editCurrent(@RequestBody @Valid CurrentTimeEntry timeEntry, Errors errors) {
        if (errors.hasErrors())
            throw new DataNotValidException(errors.getAllErrors());
        int updated = timeService.editCurrent(timeEntry);
        if (updated < 1)
            throw new UpdateFailedException("Could not update entry");
    }

    @PutMapping("/finished")
    public void editFinished(@RequestBody @Valid FinishedTimeEntry timeEntry, Errors errors) {
        if (errors.hasErrors())
            throw new DataNotValidException(errors.getAllErrors());
        int updated = timeService.editFinished(timeEntry);
        if (updated < 1)
            throw new UpdateFailedException("Could not update entry");
    }

    @DeleteMapping("/current/{id}")
    public void deleteCurrent(@PathVariable Long id) {
        int deleted = timeService.deleteCurrentById(id);
        if (deleted < 1)
            throw new UpdateFailedException("Could not update entry");
    }

    @DeleteMapping("/finished/{id}")
    public void deleteFinished(@PathVariable Long id) {
        int deleted = timeService.deleteFinishedById(id);
        if (deleted < 1)
            throw new UpdateFailedException("Could not delete entry");
    }

    @GetMapping("/total")
    public Integer totalDurationByDate(@RequestParam String date) {
        return timeService.getTotalForDate(date);
    }

    @GetMapping("/finished/total")
    public Long totalFinishedDurationForDate(@RequestParam String date) {
        return timeService.getTotalFinishedForDate(date);
    }

    @GetMapping("/total/between")
    public Long totalForPeriod(@RequestParam String startDate, @RequestParam String endDate) {
        return timeService.getTotalForPeriod(startDate, endDate);
    }

    @GetMapping("/total/month")
    public List<DateAndDurationView> monthStats(@RequestParam String date) {
        return timeService.getTotalByDateForMonth(date);
    }

    @GetMapping("/total/period")
    public List<DateAndDurationView> periodStats(@RequestParam String startDate, @RequestParam String endDate) {
        return timeService.getTotalByDateForPeriod(startDate, endDate);
    }

}
