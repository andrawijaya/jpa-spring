package com.enigmacamp.service;

import com.enigmacamp.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ICourseService {
    List<Course> list() throws Exception;

    Course create(Course course) throws Exception;

    Course get(String id) throws Exception;

    void update(Course course, String id) throws Exception;

    void delete(String id) throws Exception;

    List<Course> getBy(String keyword, String value) throws Exception;

    List<Course> getPagging(int size, int page, String sortBy, String direction) throws Exception;


    Page<Course> listPage(Integer page, Integer size, String direction, String sortBy) throws Exception;

}