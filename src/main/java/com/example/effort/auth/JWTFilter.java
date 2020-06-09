package com.example.effort.auth;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JWTFilter extends BasicAuthenticationFilter {
    private JWTService jwtService;

    public JWTFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        if (jwtService == null) {
            injectJWTService(request);
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(header);
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private void injectJWTService(HttpServletRequest request) {
        ServletContext servletCtx = request.getServletContext();
        WebApplicationContext appCtx = WebApplicationContextUtils.getWebApplicationContext(servletCtx);
        jwtService = appCtx.getBean(JWTService.class);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        String jwt = header.substring(7);
        try {
            String payload = jwtService.validateToken(jwt);
            JsonParser parser = JsonParserFactory.getJsonParser();
            Map<String, Object> jsonMap = parser.parseMap(payload);
            String username = (String) jsonMap.get("user");
            String role = (String) jsonMap.get("role");
            List<GrantedAuthority> authorities = new ArrayList<>();
            GrantedAuthority authority = (GrantedAuthority) () -> String.format("ROLE_%s", role);
            authorities.add(authority);
            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        } catch (Exception e) {
            return null;
        }
    }

}
