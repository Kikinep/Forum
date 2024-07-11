package com.kikinep.forum.domain.comment;

import jakarta.validation.constraints.NotNull;

public record CommentUpdateData(
        @NotNull
        Long id,
        String message,
        Boolean solution
) {
}
