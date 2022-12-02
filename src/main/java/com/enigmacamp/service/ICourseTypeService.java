package com.enigmacamp.service;

import com.enigmacamp.model.Course;
import com.enigmacamp.model.CourseType;

import java.util.List;

public interface ICourseTypeService {
    List<CourseType> list() throws Exception;

    CourseType create(CourseType courseType) throws Exception;

    List<Course> getCourseByTypeId(String id) throws Exception;
}
