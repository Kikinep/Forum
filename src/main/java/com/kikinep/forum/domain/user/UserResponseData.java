package com.kikinep.forum.domain.user;

public record UserResponseData(
        Long id,
        String username,
        String email,
        String password
) {
    public UserResponseData (User user) {
        this(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }
}
