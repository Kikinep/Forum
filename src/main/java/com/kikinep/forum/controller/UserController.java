package com.kikinep.forum.controller;

import com.kikinep.forum.domain.user.User;
import com.kikinep.forum.domain.user.UserRegistrationData;
import com.kikinep.forum.domain.user.UserRepository;
import com.kikinep.forum.domain.user.UserResponseData;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repository;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserResponseData> registerUser(@RequestBody @Valid UserRegistrationData data , UriComponentsBuilder uriComponentsBuilder) {
        User user = repository.save(new User(data));
        URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body(new UserResponseData(user));
    }
}
