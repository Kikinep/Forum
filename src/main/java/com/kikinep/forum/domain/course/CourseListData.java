package com.kikinep.forum.domain.course;

public record CourseListData(
        Long id,
        String name,
        Category category
) {
    public CourseListData(Course course) {
        this(course.getId(), course.getName(), course.getCategory());
    }
}
