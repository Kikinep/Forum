package com.kikinep.forum.domain.post;

import com.kikinep.forum.domain.course.Course;
import com.kikinep.forum.domain.course.CourseRepository;
import com.kikinep.forum.domain.user.User;
import com.kikinep.forum.domain.user.UserRepository;
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
        User user = userRepository.getReferenceById(data.userId());
        Course course = courseRepository.getReferenceById(data.courseId());
        Post post = postRepository.save(new Post(user, course, data));
        return new PostResponseData(post);
    }
}
