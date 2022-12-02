package com.enigmacamp.service;

import com.enigmacamp.exception.EntityExistException;
import com.enigmacamp.model.CourseInfo;
import com.enigmacamp.repository.ICourseInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseInfoService implements ICourseInfoService {
    @Autowired
    private ICourseInfoRepository courseInfoRepository;

    @Override
    public List<CourseInfo> list() throws Exception {
        List<CourseInfo> courseInfoList = courseInfoRepository.findAll();
        return courseInfoList;
    }

    @Override
    public CourseInfo create(CourseInfo courseInfo) throws Exception {
        try {
            CourseInfo newCourseInfo = courseInfoRepository.save(courseInfo);
            return newCourseInfo;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }
}
