package com.example.effort.stats;

import java.util.Map;

public interface StatsService {

    Map<String, Object> getStatsForPeriod(String startDate, String endDate);

}
