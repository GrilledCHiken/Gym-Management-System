package com.aryan.gymManagementSystem.config;

import com.aryan.gymManagementSystem.entity.Admin;
import com.aryan.gymManagementSystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AdminDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepository.findByEmail(email);
        return admin.map(CustomAdminDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("admin not found "+ email));
    }
}
