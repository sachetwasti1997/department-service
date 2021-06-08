package com.sachet.departmentservice.repository;

import com.sachet.departmentservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
