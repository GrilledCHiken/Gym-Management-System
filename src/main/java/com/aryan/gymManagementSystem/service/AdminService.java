package com.aryan.gymManagementSystem.service;

import com.aryan.gymManagementSystem.entity.Admin;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AdminService {
    Admin registerAdmin(Admin admin);
    Optional<Admin> loginAdmin(String email, String password);
    Optional<Admin> findByEmail(String email);
    void changePassword(Admin admin, String newPassword);
    boolean isEmailAlreadyExists(String email);
}