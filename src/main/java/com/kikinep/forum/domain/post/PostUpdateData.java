package com.kikinep.forum.domain.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostUpdateData(
        @NotNull
        Long id,
        String title,
        @NotBlank
        String message
) {
}
