package com.example.effort.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PasswordsDto {
    @NotNull @NotBlank
    private final String oldPassword;
    @NotNull @NotBlank @Length(min = 5, max = 30, message = "Password must be between 5 and 30 characters long")
    private final String newPassword;

    public PasswordsDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
