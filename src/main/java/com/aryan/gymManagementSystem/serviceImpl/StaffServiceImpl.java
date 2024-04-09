package com.aryan.gymManagementSystem.serviceImpl;

import com.aryan.gymManagementSystem.entity.Staff;
import com.aryan.gymManagementSystem.repository.StaffRepository;
import com.aryan.gymManagementSystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Override
    public List<Staff> getAllStaffs() {
        return staffRepository.findAll();
    }

    @Override
    public void registerStaff(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public void deleteStaff(Long staffId) {
        staffRepository.deleteById(staffId);
    }

    @Override
    public long getTotalStaffsCount() {
        return staffRepository.count();
    }
}
