package com.enigmacamp.service;

import com.enigmacamp.model.CourseInfo;

import java.util.List;

public interface ICourseInfoService {
    List<CourseInfo> list() throws Exception;

    CourseInfo create(CourseInfo courseInfo) throws Exception;
}
