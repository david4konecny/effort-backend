package com.example.effort.user;


import com.example.effort.auth.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
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
    public Map<String, String> login(HttpServletResponse response, Principal principal) {
        Map<String, String> res = new HashMap<>();
        Cookie c = createCookie("access-token", authService.getToken(), 1800);
        response.addCookie(c);
        res.put("result", "logged in");
        res.put("username", principal.getName());
        return res;
    }

    @GetMapping("/login/test")
    public void testAuthentication() {
    }

    @GetMapping("")
    public Map<String, String> getUser(Principal principal) {
        Map<String, String> res = new HashMap<>();
        res.put("username", principal.getName());
        return res;
    }

    @GetMapping("/all")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("")
    public User add(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new UserNotValidException(bindingResult.getAllErrors());
        else if (userService.usernameExists(userDto.getUsername()))
            throw new UsernameAlreadyExistsException(userDto.getUsername());
        else {
            return userService.insert(userDto.toUser(), userDto.isAddSampleData());
        }
    }

    @PutMapping("/username")
    public void editUsername(@RequestBody String newUsername) {
        if (userService.usernameExists(newUsername))
            throw new UsernameAlreadyExistsException(newUsername);
        else
            userService.editUsername(newUsername);
    }

    @PutMapping("/password")
    public void editPassword(@RequestBody Map<String, String> passwords, Authentication authentication) {
        userService.editPassword(
                passwords.get("oldPassword"), passwords.get("newPassword"), authentication.getName()
        );
    }

    @DeleteMapping
    public void deleteUser(HttpServletRequest request) throws ServletException {
        userService.deleteUser();
        request.logout();
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
