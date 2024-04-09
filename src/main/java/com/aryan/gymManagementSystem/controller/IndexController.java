package com.aryan.gymManagementSystem.controller;

import com.aryan.gymManagementSystem.service.MemberService;
import com.aryan.gymManagementSystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
@Autowired
private MemberService memberService;
@Autowired
private StaffService staffService;
    @GetMapping("/home")
    public String showHomePage(Model model) {
        model.addAttribute("title", "Home");
        long activeMembersCount = memberService.getTotalMembersCount();
        model.addAttribute("activeMembersCount", activeMembersCount);
        long activeStaffsCount = staffService.getTotalStaffsCount();
        model.addAttribute("activeStaffsCount", activeStaffsCount);
        return "home";
    }
}
