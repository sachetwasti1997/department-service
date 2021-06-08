package com.sachet.departmentservice.controller;

import com.sachet.departmentservice.entity.Address;
import com.sachet.departmentservice.entity.Department;
import com.sachet.departmentservice.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DepartmentControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    DepartmentService departmentService;

    private<T> ResponseEntity<T> getDepartment(Long id, Class<T> response){
        String get_url = "/department/"+id;
        return testRestTemplate.getForEntity(get_url, response);
    }

    private <T> ResponseEntity<T> createDepartment(Department department, Class<T> response){
        String url = "/department/create";
        return testRestTemplate.postForEntity(
                url,
                department,
                response
        );
    }

    private <T> ResponseEntity<T> saveAddress(Long departmentId, Address address, Class<T> response){
        String url = "/department/add/address/"+departmentId;
        return testRestTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(address),
                response
        );
    }

    @BeforeEach
    void cleanUp(){
        departmentService.deleteAll();
    }

    private Department createValidDepartment(){
        Department department = new Department();
        department.setDepartmentName("IT Department");
        department.setDepartmentCode("AWSIT");

        return department;
    }

    private Address createValidAddress(){
        Address address = new Address();
        address.setCity("Salbari");
        address.setState("WestBengal");
        address.setZipCode(734009);

        return address;
    }

    @Test
    void getDepartment_idNotExist_receiveBadRequest() {
        ResponseEntity<?> responseEntity = getDepartment(1L, Object.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void getDepartment_idNotExist_receiveApiError(){
        ResponseEntity<?> responseEntity = getDepartment(1L, Object.class);
        Map<String, String> response = (Map<String, String>) responseEntity.getBody();
        System.out.println(response);
        assertTrue(response.containsKey("message"));
    }

    @Test
    void getDepartment_idExists_receiveOk(){
        Department department = createValidDepartment();
        department = departmentService.save(department);

        ResponseEntity<?> responseEntity = getDepartment(department.getId(), Object.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void createDepartment_departmentWithNameAlreadyExist_receiveBadRequest(){
        Department department = createValidDepartment();
        department = departmentService.save(department);

        Department newDepartment = createValidDepartment();

        ResponseEntity<?> responseEntity = createDepartment(newDepartment, Object.class);
        System.out.println(responseEntity.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void createDepartment_departmentWithNameAlreadyExist_receiveApiError(){
        Department department = createValidDepartment();
        department = departmentService.save(department);

        ResponseEntity<?> responseEntity = createDepartment(department, Object.class);
        Map<String, String> response = (Map<String, String>) responseEntity.getBody();
        System.out.println(response);
        assertTrue(response.containsKey("message"));
    }

    @Test
    void createDepartment_departmentNameNull_receiveBadRequest(){
        Department department = createValidDepartment();
        department.setDepartmentName(null);

        ResponseEntity<?> responseEntity = createDepartment(department, Object.class);
        System.out.println(responseEntity.getBody());
    }

    @Test
    void editDepartment_saveAddress_receiveOk(){
        Department department = createValidDepartment();
        departmentService.save(department);

        Address address = createValidAddress();

        ResponseEntity<?> responseEntity = saveAddress(department.getId(), address, Object.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void editDepartment_saveAddress_departmentIdNotValid_receiveBadRequest(){
        ResponseEntity<?> responseEntity = saveAddress(1L, new Address(), Object.class);
        System.out.println(responseEntity.getBody());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}























