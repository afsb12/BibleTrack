package com.example.BibleTrack.controller;

import com.example.BibleTrack.dto.User.UserRequest;
import com.example.BibleTrack.dto.User.UserResponse;
import com.example.BibleTrack.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @PostMapping()
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        System.out.println(userRequest);
        return userService.createUser(userRequest);
    }

    @GetMapping()
    public ResponseEntity<String> listUsers() {
        try{
            var users =  userService.getAllUsers();
            String json = objectMapper.writeValueAsString(users);
            return ResponseEntity.ok(json);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(
    value = "/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id,@RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
