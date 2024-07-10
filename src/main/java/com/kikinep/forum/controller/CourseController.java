package com.kikinep.forum.controller;

import com.kikinep.forum.domain.course.Course;
import com.kikinep.forum.domain.course.CourseRegistrationData;
import com.kikinep.forum.domain.course.CourseRepository;
import com.kikinep.forum.domain.course.CourseResponseData;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<CourseResponseData> registerCourse(@RequestBody @Valid CourseRegistrationData data, UriComponentsBuilder uriComponentsBuilder) {
        Course course = repository.save(new Course(data));
        URI url = uriComponentsBuilder.path("/courses/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(url).body(new CourseResponseData(course));
    }
}
