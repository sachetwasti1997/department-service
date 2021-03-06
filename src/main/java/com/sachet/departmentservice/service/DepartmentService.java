package com.sachet.departmentservice.service;

import com.sachet.departmentservice.custom_error.DepartmentNotFoundException;
import com.sachet.departmentservice.entity.Address;
import com.sachet.departmentservice.entity.Department;
import com.sachet.departmentservice.entity.Events;

public interface DepartmentService {

    Department save(Department department);

    Department findById(Long id) throws DepartmentNotFoundException;

    Department findByName(String name);

    Department saveAddress(Long departmentId, Address address) throws DepartmentNotFoundException;

    Department createEvents(Long departmentId, Events events) throws DepartmentNotFoundException;

    void deleteAll();

}
