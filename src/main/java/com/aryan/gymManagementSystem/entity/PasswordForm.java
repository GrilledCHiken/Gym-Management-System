package com.aryan.gymManagementSystem.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordForm {

    private String currentPassword;
    private String newPassword;
    private String confirmPassword;


}