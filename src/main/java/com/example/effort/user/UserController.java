package com.example.effort.user;


import com.example.effort.auth.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    public UserController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/authenticate")
    public Map<String, String> authenticate() {
        Map<String, String> result = new HashMap<>();
        result.put("token", authService.getToken());
        return result;
    }

    @GetMapping("")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("")
    public User add(@RequestBody User user) {
        return userService.insert(user);
    }

}
