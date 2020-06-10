package com.example.effort.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JWTService jwtService;

    public AuthService(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    public String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        return jwtService.generateToken(username);
    }
}
