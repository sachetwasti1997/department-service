package com.sachet.departmentservice.entity;

import com.sachet.departmentservice.cutom_constraints.DepartmentName;
import com.sachet.departmentservice.exception_groups.OnCreate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name")
    @DepartmentName(message = "Department with that name already exist!", groups = {OnCreate.class})
    @NotNull(message = "Department Name Cannot be null!")
    private String departmentName;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_address_id")
    private Address departmentAddress;

    @Column(name = "department_code")
    @NotNull(message = "Department Code cannot be null!")
    private String departmentCode;

    @OneToMany(mappedBy = "department")
    private List<Events> events;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Address getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(Address departmentAddress) {
        this.departmentAddress = departmentAddress;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", departmentAddress=" + departmentAddress +
                ", departmentCode='" + departmentCode + '\'' +
                '}';
    }
    /**Rosy*/
}
