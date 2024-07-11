package com.kikinep.forum.controller;

import com.kikinep.forum.domain.user.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class UserController {
    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<UserResponseData> registerUser(@RequestBody @Valid UserRegistrationData data , UriComponentsBuilder uriComponentsBuilder) {
        User user = repository.save(new User(data));
        URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body(new UserResponseData(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserListData>> getUserList(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable).map(UserListData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UserResponseData> updateUser(@RequestBody @Valid UserUpdateData data) {
        User user = repository.getReferenceById(data.id());
        user.updateUser(data);
        return ResponseEntity.ok(new UserResponseData(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        boolean userExists = repository.existsById(id);
        if (userExists) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Unable to find User with id provided");
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<UserResponseData> getUser(@PathVariable Long id) {
        User user = repository.getReferenceById(id);
        return ResponseEntity.ok(new UserResponseData(user));
    }
}
