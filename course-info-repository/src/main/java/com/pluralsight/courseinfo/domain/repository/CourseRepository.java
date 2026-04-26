package com.pluralsight.courseinfo.domain.repository;

import com.pluralsight.courseinfo.domain.Course;

import java.util.List;

public interface CourseRepository {

    void saveCourse(Course course) throws RepositoryException;

    List<Course> getAllCourses() throws RepositoryException;

    static CourseRepository openCourseRepository(String databaseFile){
        return new CourseJdbcRepository(databaseFile);
    }

}
