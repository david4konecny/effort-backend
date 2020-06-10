package com.example.effort.time;

import com.example.effort.category.Category;
import com.example.effort.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class TimeSession {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name = "user_id", foreignKey = @ForeignKey())
    private User user;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    @ManyToOne @JoinColumn(name = "category_id", foreignKey = @ForeignKey())
    private Category category;
    private Integer startTime;
    private Integer endTime;
    @Transient @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer duration;

    public TimeSession() {
    }

    public TimeSession(User user, LocalDate date, Category category, Integer startTime, Integer endTime) {
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
