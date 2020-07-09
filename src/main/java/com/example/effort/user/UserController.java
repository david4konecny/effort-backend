package com.example.effort.user;


import com.example.effort.util.exceptions.DataNotValidException;
import com.example.effort.util.exceptions.UpdateFailedException;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public Map<String, String> getUsername(Principal principal) {
        Map<String, String> res = new HashMap<>();
        res.put("username", principal.getName());
        return res;
    }

    @PostMapping("")
    public User add(@RequestBody @Valid UserDto userDto, Errors errors) {
        if (errors.hasErrors())
            throw new DataNotValidException(errors.getAllErrors());
        else if (userService.usernameExists(userDto.getUsername()))
            throw new UsernameAlreadyExistsException(userDto.getUsername());
        else {
            return userService.insert(userDto.toUser(), userDto.isAddSampleData());
        }
    }

    @PutMapping("/username")
    public void editUsername(@RequestBody String newUsername) {
        if (newUsername == null || newUsername.isEmpty())
            throw new UpdateFailedException("must provide a valid username");
        if (userService.usernameExists(newUsername))
            throw new UsernameAlreadyExistsException(newUsername);
        else
            userService.editUsername(newUsername);
    }

    @PutMapping("/password")
    public void editPassword(@RequestBody @Valid PasswordsDto passwords, Errors errors, Authentication authentication) {
        if (errors.hasErrors())
            throw new DataNotValidException(errors.getAllErrors());
        userService.editPassword(
                passwords.getOldPassword(), passwords.getNewPassword(), authentication.getName()
        );
    }

    @DeleteMapping
    public void deleteUser(HttpServletRequest request) throws ServletException {
        userService.deleteUser();
        request.logout();
    }

}
