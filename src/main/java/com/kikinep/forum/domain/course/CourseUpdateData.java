package com.kikinep.forum.domain.course;

import jakarta.validation.constraints.NotNull;

public record CourseUpdateData(
        @NotNull
        Long id,
        String name,
        Category category
) {
}
