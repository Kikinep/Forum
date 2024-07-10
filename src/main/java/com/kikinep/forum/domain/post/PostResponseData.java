package com.kikinep.forum.domain.post;

import java.time.LocalDateTime;

public record PostResponseData(
        Long id,
        String title,
        String message,
        LocalDateTime date,
        Status status,
        Long userId,
        Long courseId
) {
    public PostResponseData(Post post) {
        this(post.getId(), post.getTitle(), post.getMessage(), post.getDate(), post.getStatus(), post.getUser().getId(), post.getCourse().getId());
    }
}
