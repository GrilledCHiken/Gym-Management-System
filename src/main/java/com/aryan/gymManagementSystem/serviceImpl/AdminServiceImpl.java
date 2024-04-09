package com.aryan.gymManagementSystem.serviceImpl;

import com.aryan.gymManagementSystem.entity.Admin;
import com.aryan.gymManagementSystem.repository.AdminRepository;
import com.aryan.gymManagementSystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Admin registerAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    @Override
    public Optional<Admin> loginAdmin(String email, String password) {
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if (admin != null && passwordEncoder.matches(password, admin.get().getPassword())) {
            return admin;
        }
        return null;
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public void changePassword(Admin admin, String newPassword) {
        admin.setPassword(newPassword);
        adminRepository.save(admin);
    }

    @Override
    public boolean isEmailAlreadyExists(String email) {
        return false;
    }
}
