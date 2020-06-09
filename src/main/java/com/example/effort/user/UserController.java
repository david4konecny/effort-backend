package com.example.effort.user;


import com.example.effort.auth.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/authenticate")
    public Map<String, String> authenticate() {
        Map<String, String> result = new HashMap<>();
        result.put("token", authService.getToken());
        return result;
    }

}
