package com.kikinep.forum.domain.user;

import jakarta.validation.constraints.NotNull;

public record UserUpdateData(
        @NotNull
        Long id,
        String username,
        String email,
        String password
) {
}
