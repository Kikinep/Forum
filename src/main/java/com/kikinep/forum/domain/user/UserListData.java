package com.kikinep.forum.domain.user;

public record UserListData(
        Long id,
        String username,
        String email
) {
    public UserListData (User user) {
        this(user.getId(), user.getUsername(), user.getEmail());
    }
}
