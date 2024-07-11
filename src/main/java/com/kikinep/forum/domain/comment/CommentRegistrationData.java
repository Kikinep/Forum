package com.kikinep.forum.domain.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRegistrationData(
        @NotBlank
        String message,
        @NotNull
        Long postId,
        @NotNull
        Long userId
) {
}
