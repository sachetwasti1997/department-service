package com.sachet.departmentservice.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Events implements Serializable {

    private static final long serialVersionUID;

    static {
        serialVersionUID = 43L;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_name")
    private String eventName;
    @Column(name = "event_start_time")
    private Date eventStartTime;
    @Column(name = "event_end_time")
    private Date eventEndTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "department_id")
    private Department department;

    public Events(Long id, String eventName, Date eventStartTime, Date eventEndTime) {
        this.id = id;
        this.eventName = eventName;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    public Events() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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

    @Override
    public String toString() {
        return "Events{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", eventStartTime=" + eventStartTime +
                ", eventEndTime=" + eventEndTime +
                '}';
    }
}
