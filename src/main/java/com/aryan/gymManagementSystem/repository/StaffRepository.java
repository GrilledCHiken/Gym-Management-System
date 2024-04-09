package com.aryan.gymManagementSystem.repository;

import com.aryan.gymManagementSystem.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    long count();
}
