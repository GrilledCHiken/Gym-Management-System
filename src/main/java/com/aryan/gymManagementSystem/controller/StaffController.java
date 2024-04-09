package com.aryan.gymManagementSystem.controller;

import com.aryan.gymManagementSystem.entity.Member;
import com.aryan.gymManagementSystem.entity.Staff;
import com.aryan.gymManagementSystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class StaffController {
    @Autowired
    private StaffService staffService;
    @GetMapping("/register-staff")
    public String showStaffRegistrationForm(Model model) {
        model.addAttribute("title","Register Staff");
        model.addAttribute("staff", new Staff());
        return "register-staff";
    }

    @PostMapping("/register-staff")
    public String registerStaff(Staff staff) {
        staffService.registerStaff(staff);
        return "redirect:/register-staff";
    }
    @GetMapping("/staffs")
    public String showStaffList(Model model) {
        List<Staff> staffs = staffService.getAllStaffs();
        model.addAttribute("title", "List of Staffs");
        model.addAttribute("staffs", staffs);
        return "staff-list";
    }
    @PostMapping("/removeStaff")
    public String removeStaff(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        staffService.deleteStaff(id);
        redirectAttributes.addFlashAttribute("message", "Staff removed successfully.");
        return "redirect:/staffs";
    }
}
