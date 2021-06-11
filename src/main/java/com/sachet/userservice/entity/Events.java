package com.sachet.userservice.entity;

import com.sachet.departmentservice.entity.Department;

import java.util.Date;

public class Events {

    private Long id;
    private String eventName;
    private Date eventStartTime;
    private Date eventEndTime;

    private Department department;

    public Events() {
    }

    public Events(Long id, String eventName, Date eventStartTime, Date eventEndTime, Department department) {
        this.id = id;
        this.eventName = eventName;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(Date eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public Date getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(Date eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Events{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", eventStartTime=" + eventStartTime +
                ", eventEndTime=" + eventEndTime +
                " "+department+
                '}';
    }

}
