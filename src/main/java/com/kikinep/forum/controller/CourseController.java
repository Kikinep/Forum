package com.kikinep.forum.controller;

import com.kikinep.forum.domain.course.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/courses")
@SecurityRequirement(name = "bearer-key")
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

    @GetMapping
    public ResponseEntity<Page<CourseListData>> getCourseList(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable).map(CourseListData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CourseResponseData> updateCourse(@RequestBody @Valid CourseUpdateData data) {
        Course course = repository.getReferenceById(data.id());
        course.updateCourse(data);
        return ResponseEntity.ok(new CourseResponseData(course));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Course> deleteCourse(@PathVariable Long id) {
        boolean courseExists = repository.existsById(id);
        if (courseExists) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Unable to find Course with id provided");
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseData> getCourse(@PathVariable Long id) {
        Course course = repository.getReferenceById(id);
        return ResponseEntity.ok(new CourseResponseData(course));
    }
}
