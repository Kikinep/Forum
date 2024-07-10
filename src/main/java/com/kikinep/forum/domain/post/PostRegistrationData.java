package com.kikinep.forum.domain.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostRegistrationData(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long userId,
        @NotNull
        Long courseId
) {
}
