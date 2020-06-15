package com.example.effort.user;


import com.example.effort.auth.AuthService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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

    @GetMapping("/login")
    public Map<String, String> login(HttpServletResponse response) {
        Map<String, String> res = new HashMap<>();
        Cookie c = createCookie("access-token", authService.getToken());
        response.addCookie(c);
        res.put("result", "logged in");
        return res;
    }

    @GetMapping("/login/test")
    public void testAuthentication() {
    }

    @GetMapping("")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("")
    public User add(@RequestBody User user) {
        return userService.insert(user);
    }

    private Cookie createCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/api");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(1800);
        return cookie;
    }

}
