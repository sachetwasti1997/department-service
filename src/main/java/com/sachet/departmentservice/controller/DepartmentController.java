package com.sachet.departmentservice.controller;

import com.sachet.departmentservice.custom_error.DepartmentNotFoundException;
import com.sachet.departmentservice.entity.Address;
import com.sachet.departmentservice.entity.Department;
import com.sachet.departmentservice.exception_groups.OnCreate;
import com.sachet.departmentservice.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) throws DepartmentNotFoundException {
        Department department = departmentService.findById(id);
        System.out.println(department);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping("/create")
    @Validated({OnCreate.class})
    public ResponseEntity<Department> createDepartment(@RequestBody @Valid Department department){
        return new ResponseEntity<>(departmentService.save(department), HttpStatus.OK);
    }

    @PutMapping("/add/address/{id}")
    public ResponseEntity<Department> addAddress(
            @PathVariable Long id,
            @RequestBody Address address
    ) throws DepartmentNotFoundException{
        Department department = departmentService.saveAddress(id, address);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
}
