package com.example.effort.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public Map<String, String> login(HttpServletResponse response, Principal principal) {
        Map<String, String> res = new HashMap<>();
        Cookie c = createCookie("access-token", authService.getToken(), 1800);
        response.addCookie(c);
        res.put("result", "logged in");
        res.put("username", principal.getName());
        return res;
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        response.addCookie(createCookie("access-token", "", 0));
        request.logout();
    }

    private Cookie createCookie(String name, String value, int age) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/api");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(age);
        return cookie;
    }

}
