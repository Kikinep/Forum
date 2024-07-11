package com.kikinep.forum.domain.comment;

import com.kikinep.forum.domain.post.Post;
import com.kikinep.forum.domain.post.PostRepository;
import com.kikinep.forum.domain.user.User;
import com.kikinep.forum.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentRegistrationService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public CommentResponseData register(CommentRegistrationData data) {
        boolean postExists = postRepository.existsById(data.postId());
        boolean userExists = userRepository.existsById(data.userId());

        if (postExists) {
            Post post = postRepository.getReferenceById(data.postId());

            if (userExists) {
                User user = userRepository.getReferenceById(data.userId());
                Comment comment = commentRepository.save(new Comment(post, user, data));

                return new CommentResponseData(comment);
            } else {
                throw new EntityNotFoundException("Unable to find User with provided id");
            }
        } else {
            throw new EntityNotFoundException("Unable to find Post with provided id");
        }
    }
}
