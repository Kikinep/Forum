package com.kikinep.forum.domain.comment;

import com.kikinep.forum.domain.post.Post;
import com.kikinep.forum.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Comment")
@Table(name = "comments")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private Boolean solution;

    public Comment(Post post, User user, CommentRegistrationData data) {
        this.message = data.message();
        this.post = post;
        this.date = LocalDateTime.now();
        this.user = user;
        this.solution = false;
    }

    public void updateComment(CommentUpdateData data) {
        if (data.message() != null) {
            this.message = data.message();
        }

        if (data.solution() != null) {
            this.solution = data.solution();
        }
    }
}
