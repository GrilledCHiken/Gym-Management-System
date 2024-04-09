package com.aryan.gymManagementSystem.repository;

import com.aryan.gymManagementSystem.entity.GymEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymEquipmentRepository extends JpaRepository<GymEquipment, Long> {
}
