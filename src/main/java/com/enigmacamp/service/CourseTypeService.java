package com.enigmacamp.service;

import com.enigmacamp.exception.EntityExistException;
import com.enigmacamp.model.Course;
import com.enigmacamp.model.CourseType;
import com.enigmacamp.repository.ICourseRepository;
import com.enigmacamp.repository.ICourseTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseTypeService implements ICourseTypeService {

    @Autowired
    private ICourseTypeRepository courseTypeRepository;

    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public List<CourseType> list() throws Exception {
        List<CourseType> courseTypes = courseTypeRepository.findAll();
        return courseTypes;
    }

    @Override
    public CourseType create(CourseType courseType) throws Exception {
        try {
            CourseType newCourseType = courseTypeRepository.save(courseType);
            return newCourseType;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public List<Course> getCourseByTypeId(String id) throws Exception {
        return courseRepository .findAll().stream().filter(course-> course.getCourseType().getTypeId().equals(id)).collect(Collectors.toList());
    }
}
