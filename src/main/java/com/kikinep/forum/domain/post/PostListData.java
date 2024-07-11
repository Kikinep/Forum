package com.kikinep.forum.domain.post;

import com.kikinep.forum.domain.comment.Comment;
import com.kikinep.forum.domain.comment.CommentListData;

import java.time.LocalDateTime;
import java.util.List;

public record PostListData(
        Long id,
        String title,
        String message,
        LocalDateTime date,
        Status status,
        Long user_id,
        Long course_id,
        List<CommentListData> comments
) {
    public PostListData (Post post) {
        this(post.getId(), post.getTitle(), post.getMessage(), post.getDate(), post.getStatus(), post.getUser().getId(), post.getCourse().getId(), post.getComments().stream().map(CommentListData::new).toList());
    }
}
