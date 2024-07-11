package com.kikinep.forum.domain.comment;

import java.time.LocalDateTime;

public record CommentResponseData(
        Long id,
        String message,
        Long postId,
        LocalDateTime date,
        Long userId,
        Boolean solution
) {
    public CommentResponseData(Comment comment) {
        this(comment.getId(), comment.getMessage(), comment.getPost().getId(), comment.getDate(), comment.getUser().getId(), comment.getSolution());
    }
}
