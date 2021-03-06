package com.example.effort.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAll();
    User insert(User user, boolean addSampleData);
    boolean usernameExists(String username);
    void addSampleData(User user);
    void editUsername(String newUsername);
    void editPassword(String oldPassword, String newPassword, String username);
    void deleteUser();

}
