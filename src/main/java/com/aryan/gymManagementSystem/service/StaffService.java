package com.aryan.gymManagementSystem.service;

import com.aryan.gymManagementSystem.entity.Member;
import com.aryan.gymManagementSystem.entity.Staff;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StaffService {
    List<Staff> getAllStaffs();
    void registerStaff(Staff staff);
    void deleteStaff(Long staffId);
    long getTotalStaffsCount();
}
