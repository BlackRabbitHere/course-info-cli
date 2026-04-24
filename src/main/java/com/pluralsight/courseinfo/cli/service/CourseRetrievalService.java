package com.pluralsight.courseinfo.cli.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CourseRetrievalService {
    private static final String PS_URL="https://app.pluralsight.com/profile/data/author/%s/all-content";
//    https://raw.githubusercontent.com/sandermak-ps/course-info-java-17/master/sander-mak.json fallback
    private static final HttpClient CLIENT=HttpClient
        .newBuilder()
        .followRedirects(HttpClient.Redirect.ALWAYS)
        .build();

    public String getCourseFor(String authorId){
        HttpRequest request=HttpRequest
                .newBuilder(URI.create(PS_URL.formatted(authorId)))
                .GET()
                .build();
        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return switch(response.statusCode()){
                case 200-> response.body();
                case 404-> "";
                default -> throw new RuntimeException("Pluralsight API call failed with status code "+response.statusCode());
            };
        }catch (IOException |InterruptedException e){
            throw new RuntimeException("Could not call Pluralsight API",e);
        }


    }

}
