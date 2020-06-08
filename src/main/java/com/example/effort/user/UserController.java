package com.example.effort.user;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/authenticate")
    public Map<String, String> authenticate() {
        Map<String, String> result = new HashMap<>();
        result.put("result", "ok");
        return result;
    }

}
