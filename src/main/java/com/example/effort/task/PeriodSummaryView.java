package com.example.effort.task;

public class PeriodSummaryView {
    private Long count;
    private Long finished;

    public PeriodSummaryView() {
    }

    public PeriodSummaryView(Long count, Long finished) {
        this.count = count;
        this.finished = finished;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getFinished() {
        return finished;
    }

    public void setFinished(Long finished) {
        this.finished = finished;
    }
}
