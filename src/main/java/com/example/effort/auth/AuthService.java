package com.example.effort.auth;

import com.example.effort.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class AuthService {
    private static final int ONE_DAY_IN_SECONDS = 86_400;
    private final JWTService jwtService;

    public AuthService(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    public Cookie getCookieWithToken() {
        String token = getToken();
        return createCookie("access-token", token, ONE_DAY_IN_SECONDS);
    }

    public Cookie getInvalidationCookieWithToken() {
        return createCookie("access-token", "", 0);
    }

    private String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return jwtService.generateToken(user.getId(), user.getUsername());
    }

    private Cookie createCookie(String name, String value, int expirationInSeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/api");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(expirationInSeconds);
        return cookie;
    }
}
