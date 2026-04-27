package com.pluralsight.courseinfo.server;


import com.pluralsight.courseinfo.domain.Course;
import com.pluralsight.courseinfo.domain.repository.CourseRepository;
import com.pluralsight.courseinfo.domain.repository.RepositoryException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("/courses")
public class CourseResource {
    private static final Logger LOG= LoggerFactory.getLogger(CourseResource.class);

    private final CourseRepository courseRepository;

    public CourseResource(CourseRepository courseRepository){
        this.courseRepository=courseRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses(){
        try{
            return courseRepository
                    .getAllCourses()
                    .stream()
                    .sorted(Comparator.comparing(Course::id))
                    .toList();
        }catch (RepositoryException e){
            LOG.error("Repository exception occurred" ,e);
            throw new NotFoundException();

        }

//        return List.of();

    }
}
