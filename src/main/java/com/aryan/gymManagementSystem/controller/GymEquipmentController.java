package com.aryan.gymManagementSystem.controller;

import com.aryan.gymManagementSystem.entity.GymEquipment;
import com.aryan.gymManagementSystem.entity.Member;
import com.aryan.gymManagementSystem.repository.GymEquipmentRepository;
import com.aryan.gymManagementSystem.service.GymEquipmentService;
import com.aryan.gymManagementSystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class GymEquipmentController {
    @Autowired
    private GymEquipmentService gymEquipmentService;
    @Autowired
    private GymEquipmentRepository gymEquipmentRepository;
    @GetMapping("/add-equipment")
    public String showAddEquipmentForm(Model model) {
        model.addAttribute("title","Add Equipment");
        model.addAttribute("equipment", new GymEquipment());
        return "add-equipment";
    }

    @PostMapping("/add-equipment")
    public String addEquipment(GymEquipment gymEquipment) {
        gymEquipmentService.addEquipment(gymEquipment);
        return "redirect:/add-equipment";
    }
    @GetMapping("/equipmentList")
    public String showEquipmentList(Model model) {
        List<GymEquipment> gymEquipments = gymEquipmentService.getGymEquipments();
        model.addAttribute("title", "List of Equipments");
        model.addAttribute("equipments", gymEquipments);
        return "equipment-list";
    }
    @PostMapping("/removeEquipment")
    public String removeEquipment(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        gymEquipmentService.deleteEquipment(id);
        redirectAttributes.addFlashAttribute("message", "Equipment removed successfully.");
        return "redirect:/equipmentList";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        GymEquipment gymEquipment = gymEquipmentRepository.findById(id).orElseThrow();
        model.addAttribute("gymEquipment", gymEquipment);
        return "update-gym";
    }

    @PostMapping("/update-gym/{id}")
    public String updateEquipment(@ModelAttribute("gymEquipment") GymEquipment gymEquipment) {
        gymEquipmentService.updateEquipment(gymEquipment);
        return "redirect:/equipmentList";
    }
}
