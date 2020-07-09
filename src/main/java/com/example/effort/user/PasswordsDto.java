package com.example.effort.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PasswordsDto {
    @NotNull @NotBlank
    private final String oldPassword;
    @NotNull @NotBlank @Size(min = 5, max = 30, message = "Password must be between 5 and 30 characters long")
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
