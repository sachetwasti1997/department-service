package com.sachet.departmentservice.service;

import com.sachet.departmentservice.custom_error.DepartmentNotFoundException;
import com.sachet.departmentservice.entity.Address;
import com.sachet.departmentservice.entity.Department;
import com.sachet.departmentservice.repository.AddressRepository;
import com.sachet.departmentservice.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final AddressRepository addressRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, AddressRepository addressRepository) {
        this.departmentRepository = departmentRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department findById(Long id) throws DepartmentNotFoundException {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null){
            throw new DepartmentNotFoundException("Requested department not found");
        }
        return department;
    }

    @Override
    public Department findByName(String name) {
        return departmentRepository.findByDepartmentName(name);
    }

    @Override
    public Department saveAddress(Long departmentId, Address address) throws DepartmentNotFoundException {
        Department department = findById(departmentId);
        if (department == null){
            throw new DepartmentNotFoundException("Department with given Id not found");
        }
        department.setDepartmentAddress(address);
        return departmentRepository.save(department);
    }

    @Override
    public void deleteAll() {
        departmentRepository.deleteAll();
    }
}














