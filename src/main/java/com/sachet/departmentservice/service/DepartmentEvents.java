package com.sachet.departmentservice.service;

import com.sachet.departmentservice.custom_error.DepartmentNotFoundException;
import com.sachet.departmentservice.entity.Department;
import com.sachet.departmentservice.entity.Events;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;


@Component
public class DepartmentEvents {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentEvents.class);

    private final DepartmentService departmentService;

    public DepartmentEvents(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @JmsListener(destination = "test-queue", containerFactory = "myFactory")
    public void listen(Object events){
        LOGGER.info("Consuming {}", events);
        Map<String, Object> mapp = (Map<String, Object>) events;
        System.out.println(mapp.get("text"));
//        try {
//            Department department = departmentService.findById(events.getDepartment().getId());
//            if (department.getEvents() == null){
//                department.setEvents(new ArrayList<>());
//            }
//            department.getEvents().add(events);
//            LOGGER.info("Event saved!");
//        } catch (DepartmentNotFoundException e) {
//            LOGGER.info("Department not found!");
//        }
    }

}
