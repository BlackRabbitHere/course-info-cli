package com.pluralsight.courseinfo.server;


import com.pluralsight.courseinfo.domain.Course;
import com.pluralsight.courseinfo.domain.repository.CourseRepository;
import com.pluralsight.courseinfo.domain.repository.RepositoryException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/courses")
public class CourseResource {
    private static final Logger LOG= LoggerFactory.getLogger(CourseResource.class);

    private final CourseRepository courseRepository;

    public CourseResource(CourseRepository courseRepository){
        this.courseRepository=courseRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Stream<Course> getCourses(){
        try{
            return courseRepository
                    .getAllCourses()
                    .stream()
                    .sorted(Comparator.comparing(Course::id));
        }catch (RepositoryException e){
            LOG.error("Repository exception occurred" ,e);
            throw new NotFoundException();

        }

//        return List.of();

    }


    @POST
    @Path("/{id}/notes")
    @Consumes(MediaType.TEXT_PLAIN)
    public void addNotes(@PathParam("id") String id,String notes){
        try {
            courseRepository.addNotes(id, notes);
        }catch (RepositoryException e){
            LOG.error("Error in add notes ",e);
        }

    }

}
