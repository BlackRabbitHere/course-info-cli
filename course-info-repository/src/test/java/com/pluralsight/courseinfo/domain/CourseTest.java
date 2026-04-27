package com.pluralsight.courseinfo.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void shouldThrowWhenIdIsEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                new Course("", "abc", 12, "https://url", Optional.empty())
        );
    }
    @Test
    void name() {
        assertThrows(IllegalArgumentException.class, () ->
                new Course("123", "title", 12, "https://url",Optional.of(""))
        );
    }

    @Test
    void url() {
        assertThrows(IllegalArgumentException.class, () ->
                new Course("123", "abc", 12, "",Optional.empty())
        );
    }
}