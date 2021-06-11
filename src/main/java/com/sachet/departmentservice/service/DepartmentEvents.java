package com.sachet.departmentservice.service;

import com.sachet.departmentservice.custom_error.DepartmentNotFoundException;
import com.sachet.departmentservice.entity.Department;
import com.sachet.userservice.entity.Events;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.transaction.Transactional;
import java.io.IOException;
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
    @Transactional
    public void listen(Events events)throws JMSException {
        LOGGER.info("Consuming {}", events);
        com.sachet.departmentservice.entity.Events eventsOri = new com.sachet.departmentservice.entity.Events();
//        eventsOri.setDepartment(events.getDepartment());
        eventsOri.setEventName(events.getEventName());
        eventsOri.setEventStartTime(events.getEventStartTime());
        eventsOri.setEventEndTime(events.getEventEndTime());
//        System.out.println(events.getProperty("text"));
        try {
            Department department = departmentService.findById(events.getDepartment().getId());
//            eventsOri.setDepartment(department);
            if (department.getEvents() == null){
                department.setEvents(new ArrayList<>());
            }
            department.getEvents().add(eventsOri);
            eventsOri.setDepartment(department);
            departmentService.save(department);
            LOGGER.info("Event saved!");
        } catch (DepartmentNotFoundException e) {
            LOGGER.info("Department not found!");
        }
    }

}
