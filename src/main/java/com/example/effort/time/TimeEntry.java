package com.example.effort.time;

import com.example.effort.category.Category;
import com.example.effort.user.User;
import com.example.effort.util.validation.TimeConsistent;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@TimeConsistent
public abstract class TimeEntry {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne @JoinColumn(name = "user_id", foreignKey = @ForeignKey())
    private User user;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    @ManyToOne @JoinColumn(name = "category_id", foreignKey = @ForeignKey())
    private Category category;
    @NotNull @Min(0) @Max(86399)
    private Integer startTime;
    @NotNull @Min(0) @Max(86399)
    private Integer endTime;
    @Transient @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer duration;

    public TimeEntry() {
    }

    public TimeEntry(User user, LocalDate date, Category category, Integer startTime, Integer endTime) {
        this.user = user;
        this.date = date;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getDuration() {
        return endTime - startTime;
    }
}
