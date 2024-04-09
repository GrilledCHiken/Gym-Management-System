package com.aryan.gymManagementSystem.controller;

import com.aryan.gymManagementSystem.config.AdminDetailsServiceImpl;
import com.aryan.gymManagementSystem.entity.Admin;
import com.aryan.gymManagementSystem.entity.PasswordForm;
import com.aryan.gymManagementSystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminDetailsServiceImpl adminDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping({"/signIn", "/"})
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/signIn")
    public String loginAdmin(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             Model model) {

        UserDetails admin = adminDetailsService.loadUserByUsername(email);

        if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
            return "redirect:/home";
        } else {
            // Admin login failed
            model.addAttribute("error", "Invalid email or password.");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("admin", new Admin());
        return "register";
    }

    @PostMapping("/register")
    public String registerAdmin(Admin admin, BindingResult bindingResult) {
        if (adminService.isEmailAlreadyExists(admin.getEmail())) {
            bindingResult.rejectValue("email", "error.admin", "Email already exists");
        }
        if (!admin.getPassword().equals(admin.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.admin", "Passwords do not match");
        }
        if (admin.getPassword().length() <= 7) {
            bindingResult.rejectValue("password", "error.admin", "Password must be at least 8 characters");
        }
        if (!(admin.getMobileNumber().length() == 10)) {
            bindingResult.rejectValue("mobileNumber", "error.admin", "Mobile Number must be 10 digits");
        }
        if (bindingResult.hasErrors()) {
            return "register";
        }
        adminService.registerAdmin(admin);
        return "redirect:/signIn";
    }

    @GetMapping("/change-password")
    public String changePasswordForm(Model model) {
        model.addAttribute("passwordForm", new PasswordForm());
        return "change-password";
    }

    //    @PostMapping("/change-password")
//    public String changePasswordSubmit(@ModelAttribute("passwordForm") PasswordForm passwordForm,
//                                       Authentication authentication, Model model) {
//        System.out.println("123");
//        Optional<Admin> adminOptional = adminService.findByEmail(authentication.getName());
//        System.out.println(adminOptional);
//        System.out.println("hello");
//        if (adminOptional.isPresent()) {
//            Admin admin = adminOptional.get();
//
//            if (!passwordEncoder.matches(passwordForm.getCurrentPassword(), admin.getPassword())) {
//                model.addAttribute("currentPasswordError", true);
//                return "change-password";
//            }
//
//            adminService.changePassword(admin, passwordEncoder.encode(passwordForm.getNewPassword()));
//            return "redirect:/home";
//        }
//
//
//        return "redirect:/change-password";
//    }
//}
    @PostMapping("/change-password")
    public String changePasswordSubmit(@ModelAttribute("passwordForm") PasswordForm passwordForm,
                                       Authentication authentication, Model model, BindingResult bindingResult) {
        System.out.println("outside if");
        if (authentication != null && authentication.isAuthenticated()) {
            System.out.println("inside if");
            String email = authentication.getName();
            System.out.println(email);
            Optional<Admin> adminOptional = adminService.findByEmail(email);
            System.out.println(adminOptional);
            if (adminOptional.isPresent()) {
                Admin admin = adminOptional.get();
                if (!passwordForm.getNewPassword().equals(passwordForm.getConfirmPassword())) {
                    bindingResult.rejectValue("confirmPassword", "error.admin", "Passwords do not match");
                }
                if (passwordForm.getNewPassword().length() <= 7) {
                    bindingResult.rejectValue("newPassword", "error.admin", "Password must be at least 8 characters");
                }
                if (!passwordEncoder.matches(passwordForm.getCurrentPassword(), admin.getPassword())) {
                    model.addAttribute("currentPasswordError", true);
                    return "change-password";
                }
                if (bindingResult.hasErrors()) {
                    return "change-password";
                }
                adminService.changePassword(admin, passwordEncoder.encode(passwordForm.getNewPassword()));
                return "redirect:/home";
            }

        }
        return "redirect:/change-password";
    }
}