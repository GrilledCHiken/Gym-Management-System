package com.aryan.gymManagementSystem.serviceImpl;

import com.aryan.gymManagementSystem.entity.GymEquipment;
import com.aryan.gymManagementSystem.repository.GymEquipmentRepository;
import com.aryan.gymManagementSystem.service.GymEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GymEquipmentServiceImpl implements GymEquipmentService {
    @Autowired
    private GymEquipmentRepository gymEquipmentRepository;
    @Override
    public List<GymEquipment> getGymEquipments() {
        return gymEquipmentRepository.findAll();
    }

    @Override
    public void addEquipment(GymEquipment gymEquipment) {
        gymEquipmentRepository.save(gymEquipment);
    }
    @Override
    public GymEquipment updateEquipment(GymEquipment gymEquipment) {
        if (gymEquipment.getId() != null){
            gymEquipment.setId(gymEquipment.getId());
        }
        if (gymEquipment.getName() != null){
            gymEquipment.setName(gymEquipment.getName());
        }
        if (gymEquipment.getQuantity() != null){
            gymEquipment.setQuantity(gymEquipment.getQuantity());
        }


        GymEquipment updatedGym = gymEquipmentRepository.save(gymEquipment);
        return updatedGym;
    }
    @Override
    public void deleteEquipment(Long id) {
        gymEquipmentRepository.deleteById(id);
    }
}
