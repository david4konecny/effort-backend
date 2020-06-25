package com.example.effort.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAll();
    User insert(User user);
    boolean usernameExists(String username);
    void addSampleData(User user);
    void editUsername(String newUsername);

}
