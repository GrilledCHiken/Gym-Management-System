package com.aryan.gymManagementSystem.service;

import com.aryan.gymManagementSystem.entity.GymEquipment;
import com.aryan.gymManagementSystem.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GymEquipmentService {
    List<GymEquipment> getGymEquipments();
    void addEquipment(GymEquipment gymEquipment);
    void deleteEquipment(Long id);
    GymEquipment updateEquipment(GymEquipment gymEquipment);
}
