package com.example.effort.auth;

import com.example.effort.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        return jwtService.generateToken(user.getId(), user.getUsername());
    }
}
