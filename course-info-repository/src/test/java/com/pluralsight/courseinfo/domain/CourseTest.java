package com.pluralsight.courseinfo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void shouldThrowWhenIdIsEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                new Course("", "abc", 12, "https://url")
        );
    }
    @Test
    void name() {
        assertThrows(IllegalArgumentException.class, () ->
                new Course("123", "", 12, "https://url")
        );
    }

    @Test
    void url() {
        assertThrows(IllegalArgumentException.class, () ->
                new Course("123", "abc", 12, "")
        );
    }
}