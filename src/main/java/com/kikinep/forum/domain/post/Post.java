package com.kikinep.forum.domain.post;

import com.kikinep.forum.domain.course.Course;
import com.kikinep.forum.domain.comment.Comment;
import com.kikinep.forum.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Post")
@Table(name = "posts")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    public Post(User user, Course course, PostRegistrationData data) {
        this.title = data.title();
        this.message = data.message();
        this.date = LocalDateTime.now();
        this.status = Status.ACTIVE;
        this.user = user;
        this.course = course;
    }

    public void updatePost(PostUpdateData data) {
        this.message = data.message();

        if (data.title() != null) {
            this.title = data.title();
        }
    }
}
