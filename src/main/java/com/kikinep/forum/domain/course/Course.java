package com.kikinep.forum.domain.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Course")
@Table(name = "courses")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;

    public Course(CourseRegistrationData data) {
        this.name = data.name();
        this.category = data.category();
    }

    public void updateCourse(CourseUpdateData data) {
        if (data.name() != null) {
            this.name = data.name();
        }

        if (data.category() != null) {
            this.category = data.category();
        }
    }
}
