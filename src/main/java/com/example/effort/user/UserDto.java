package com.example.effort.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters long")
    private String username;
    @NotBlank(message = "Password is required")
    @Size(min = 5, max = 30, message = "Password must be between 5 and 30 characters long")
    private String password;
    private boolean addSampleData;

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAddSampleData() {
        return addSampleData;
    }

    public void setAddSampleData(boolean addSampleData) {
        this.addSampleData = addSampleData;
    }

    public User toUser() {
        User user = new User();
        user.setId(null);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
