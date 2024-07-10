package com.kikinep.forum.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserAuthenticationData(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
