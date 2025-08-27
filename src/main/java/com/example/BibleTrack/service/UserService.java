package com.example.BibleTrack.service;

import com.example.BibleTrack.dto.User.UserRequest;
import com.example.BibleTrack.dto.User.UserResponse;
import com.example.BibleTrack.entity.UserAccount;
import com.example.BibleTrack.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserService (UserRepository userRepository,ObjectMapper objectMapper){
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    public UserResponse createUser(UserRequest userRequest) {
        UserAccount user = new UserAccount();
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setUsername(userRequest.getUsername());

        UserAccount saved = userRepository.save(user);

        return toResponse(saved);
    }

    public List<UserResponse> getAllUsers() {
        List<UserResponse> users =  userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return users;
    }

    public UserResponse getUserById(long id) {
        Optional<UserAccount> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));

        return toResponse(user.get());
    }

    public UserResponse updateUser(long id, UserRequest userRequest) {
        Optional<UserAccount> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
        user.get().setEmail(userRequest.getEmail());
        user.get().setPassword(userRequest.getPassword());
        user.get().setUsername(userRequest.getUsername());
        UserAccount saved = userRepository.save(user.get());
        return toResponse(saved);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponse toResponse(UserAccount user) {
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setUsername(user.getUsername());
        res.setEmail(user.getEmail());
        return res;
    }
}
