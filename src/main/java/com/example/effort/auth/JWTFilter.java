package com.example.effort.auth;

import com.example.effort.user.User;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JWTFilter extends BasicAuthenticationFilter {
    private JWTService jwtService;

    public JWTFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            chain.doFilter(request, response);
            return;
        }
        Cookie cookie = getTokenFromCookies(cookies);
        if (cookie == null) {
            chain.doFilter(request, response);
            return;
        }
        if (jwtService == null)
            injectJWTService(request);
        UsernamePasswordAuthenticationToken authentication = getAuthentication(cookie.getValue());
        if (authentication != null)
            SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private Cookie getTokenFromCookies(Cookie[] cookies) {
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("access-token")) {
                cookie = c;
            }
        }
        return cookie;
    }

    private void injectJWTService(HttpServletRequest request) {
        ServletContext servletCtx = request.getServletContext();
        WebApplicationContext appCtx = WebApplicationContextUtils.getWebApplicationContext(servletCtx);
        jwtService = appCtx.getBean(JWTService.class);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            String payload = jwtService.validateToken(token);
            JsonParser parser = JsonParserFactory.getJsonParser();
            Map<String, Object> jsonMap = parser.parseMap(payload);
            Long id = (Long) jsonMap.get("id");
            String username = (String) jsonMap.get("username");
            User user = new User();
            user.setId(id);
            user.setUsername(username);
            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        } catch (Exception e) {
            return null;
        }
    }

}
