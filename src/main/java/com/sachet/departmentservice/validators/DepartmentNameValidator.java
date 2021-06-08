package com.sachet.departmentservice.validators;

import com.sachet.departmentservice.cutom_constraints.DepartmentName;
import com.sachet.departmentservice.entity.Department;
import com.sachet.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepartmentNameValidator implements ConstraintValidator<DepartmentName, String> {

    @Autowired
    DepartmentService departmentService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Department department = departmentService.findByName(s);
        System.out.println(department);
        return department == null;
    }
}
