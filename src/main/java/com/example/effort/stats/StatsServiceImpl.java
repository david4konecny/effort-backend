package com.example.effort.stats;

import com.example.effort.review.ReviewRepository;
import com.example.effort.task.TaskRepository;
import com.example.effort.time.DateAndDurationView;
import com.example.effort.time.TimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class StatsServiceImpl implements StatsService {
    private final TaskRepository taskRepository;
    private final TimeRepository timeRepository;
    private final ReviewRepository reviewRepository;

    public StatsServiceImpl(TaskRepository taskRepository, TimeRepository timeRepository, ReviewRepository reviewRepository) {
        this.taskRepository = taskRepository;
        this.timeRepository = timeRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    @Override
    public Map<String, Object> getStatsForPeriod(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        Map<String, Object> result = new HashMap<>();
        result.put("totalTime", timeRepository.getTotalForPeriod(start, end));
        result.put("tasks", taskRepository.getSummaryForPeriod(start, end));
        result.put("averageRating", reviewRepository.getAverageRatingForPeriod(start, end));
        return result;
    }

}
