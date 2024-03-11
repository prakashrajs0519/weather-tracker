package com.App.weatherTracker.model.bo;

import javax.persistence.*;

@Entity
@Table(name = "USER_SCHEDULE")
public class UserSchedule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_SCHEDULE_ID")
    private Long userScheduleId;
    
    @Column(name = "USER_ID")
    private Long userId;
    
    @Column(name = "CRON_EXPRESSION")
    private String cronExpression;
    
    // Constructors, Getters, and Setters
    // Constructor(s)
    public UserSchedule() {
    }

    public UserSchedule(Long userId, String cronExpression) {
        this.userId = userId;
        this.cronExpression = cronExpression;
    }

    // Getters and Setters
    public Long getUserScheduleId() {
        return userScheduleId;
    }

    public void setUserScheduleId(Long userScheduleId) {
        this.userScheduleId = userScheduleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
    
    // toString method for debugging or logging purposes
    @Override
    public String toString() {
        return "UserSchedule{" +
                "userScheduleId=" + userScheduleId +
                ", userId=" + userId +
                ", cronExpression='" + cronExpression + '\'' +
                '}';
    }
}
