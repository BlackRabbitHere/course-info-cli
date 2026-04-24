package com.pluralsight.courseinfo.cli;

import com.pluralsight.courseinfo.cli.service.CourseRetrievalService;
import com.pluralsight.courseinfo.cli.service.PluralsightCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Predicate;

public class CourseRetriever {

    private static final Logger LOG= LoggerFactory.getLogger(CourseRetriever.class);
    public static void main(String[] args) {
        LOG.info("CourseRetriever starting!");
        if(args.length==0){
            LOG.warn("Please provide an author name as argument");
            return;
        }
        
        try {
            retrieveCourses(args[0]);
//            PluralsightCourse course=new PluralsightCourse("id","title","00:54:36","https://url",false);
//            LOG.info("course: {}",course);
        }catch (Exception e){
            LOG.error("Unexpected Error",e);
        }
    }

    private static void retrieveCourses(String authorId) {

        LOG.info("Retrieving courses for author '{}'",authorId);
        CourseRetrievalService courseRetrievalService=new CourseRetrievalService();

        List<PluralsightCourse> courseToStore= courseRetrievalService.getCourseFor(authorId)
                .stream()
                .filter(Predicate.not(PluralsightCourse::isRetired))//course->!course.isRetired()
                .toList();

        LOG.info("Retrieved the following {} courses {}",courseToStore.size(),courseToStore);
    }
}
