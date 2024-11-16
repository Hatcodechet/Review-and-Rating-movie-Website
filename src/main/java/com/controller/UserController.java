package com.controller;

import com.model.User;
import com.service.UserService;
import com.utils.annotation.ApiMessage;
import com.utils.error.ResInvalidException;
import java.util.List;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService uService, PasswordEncoder pEncoder) {
        this.userService = uService;
        this.passwordEncoder = pEncoder;
    }

    @PostMapping("/users")
    @ApiMessage("Created new user")
    public ResponseEntity<User> createNewUser(@Valid @RequestBody User inputUser) throws ResInvalidException {
        boolean isEmailExist = this.userService.isEmailExist(inputUser.getEmail());
        boolean isUsernameExist = this.userService.isUsernameExist(inputUser.getUsername());
        if (isEmailExist) {
            throw new ResInvalidException("Email is exists ");
        }
        if (isUsernameExist) {
            throw new ResInvalidException("Username is exists");
        }

        String hashPass = this.passwordEncoder.encode(inputUser.getPassword());
        inputUser.setPassword(hashPass);
        User resUser = this.userService.handleCreateUser(inputUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(resUser);
    }

    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUserByID(@PathVariable("id") long id) throws ResInvalidException {
        User targetUser = this.userService.fetchUserById(id);
        if (targetUser == null) {
            throw new ResInvalidException("User with this id is not exists");
        }
        this.userService.handleDeleteUser(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/users")
    @ApiMessage("This user has been updated")
    public ResponseEntity<User> updateUser(@RequestBody User inputUser) throws ResInvalidException {
        User targetUser = this.userService.handleUpdateUser(inputUser);
        if (targetUser == null) {
            throw new ResInvalidException("This user doesn't exist");
        }
        return ResponseEntity.ok(targetUser);
    }

    @GetMapping("/users/{id}")
    @ApiMessage("You get the object successful")
    public ResponseEntity<User> getUserByID(@PathVariable("id") long id) throws ResInvalidException {
        User targetUser = this.userService.fetchUserById(id);
        if (targetUser == null) {
            throw new ResInvalidException("This user doesn't exist");
        }
        return ResponseEntity.ok(targetUser);
    }

    @GetMapping("/users")
    @ApiMessage("get objects successful")
    public ResponseEntity<List<User>> getAllUsers() throws ResInvalidException {
        List<User> allUsers = this.userService.fetchAllUser();
        return ResponseEntity.ok(allUsers);
    }

}
