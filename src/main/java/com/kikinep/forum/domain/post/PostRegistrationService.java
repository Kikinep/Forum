package com.kikinep.forum.domain.post;

import com.kikinep.forum.domain.course.Course;
import com.kikinep.forum.domain.course.CourseRepository;
import com.kikinep.forum.domain.user.User;
import com.kikinep.forum.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostRegistrationService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public PostResponseData register(PostRegistrationData data) {
        boolean userExists = userRepository.existsById(data.userId());
        boolean courseExists = courseRepository.existsById(data.courseId());

        if (userExists) {
            User user = userRepository.getReferenceById(data.userId());

            if (courseExists) {
                Course course = courseRepository.getReferenceById(data.courseId());
                Post post = postRepository.save(new Post(user, course, data));

                return new PostResponseData(post);
            } else {
                throw new EntityNotFoundException("Unable to find Course with provided id");
            }
        } else {
            throw new EntityNotFoundException("Unable to find User with provided id");
        }
    }
}
