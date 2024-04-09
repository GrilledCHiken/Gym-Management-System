package com.aryan.gymManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String address;
    private String membershipId;
    private String membershipType;
    private String dob;
    private String email;
    private LocalDate subscribedDate;
    private LocalDate expirationDate;
}
