package com.enigmacamp.service;

import com.enigmacamp.exception.NotFoundException;
import com.enigmacamp.model.Course;
import com.enigmacamp.repository.ICourseArrayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CourseArrayService implements ICourseService {

    @Value("2")
    Integer dataLength;

    @Autowired
    private ICourseArrayRepository courseRepository;

    @Override
    public List<Course> list() throws Exception {
        List<Course> result = courseRepository.getAll();
        if (result.isEmpty()) {
            throw new NotFoundException();
        }
        return result;
    }

    @Override
    public Course create(Course course) throws Exception {

        if(!(courseRepository.getAll().size()<dataLength)){
            throw new Exception("Data is full");
        }
        return courseRepository.create(course);
//        try {
//            return courseRepository.create(course);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public Course get(String id) throws Exception {
        Optional<Course> result = courseRepository.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException();
        }
        return result.get();
//        try {
//            return courseRepository.findById(id);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void update(Course course, String id) {
        try {
            Course existingCourse = get(id);
            courseRepository.update(course, existingCourse.getCourseId());
        } catch (Exception e) {
            throw new NotFoundException("Update Failed Because Id Not Found");
        }
    }

    @Override
    public void delete(String id) throws Exception {
        try{
            Course course = get(id);
            courseRepository.delete(course.getCourseId());
        }catch (Exception e){
            throw new Exception(e);
        }
//        try {
//            courseRepository.delete(id);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public List<Course> getBy(String keyword, String value) throws Exception {
       var result = courseRepository.getBy(keyword,value);
        if(result.isEmpty()){
            throw new NotFoundException("Course Not Found");
        }
        return result;
//        try {
//            return courseRepository.getBy(keyword, value);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public List<Course> getPagging(int size, int page, String sortBy, String direction) throws Exception {
        return null;
    }

    @Override
    public Page<Course> listPage(Integer page, Integer size, String direction, String sortBy) throws Exception {
        return null;
    }
}
