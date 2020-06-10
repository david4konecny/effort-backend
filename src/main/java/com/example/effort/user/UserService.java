package com.example.effort.user;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User insert(User user);

}
