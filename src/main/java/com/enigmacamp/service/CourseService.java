package com.enigmacamp.service;

import com.enigmacamp.exception.EntityExistException;
import com.enigmacamp.exception.NotFoundException;
import com.enigmacamp.model.Course;
import com.enigmacamp.model.CourseType;
import com.enigmacamp.repository.ICourseInfoRepository;
import com.enigmacamp.repository.ICourseRepository;
import com.enigmacamp.repository.ICourseTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CourseService implements ICourseService {
    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private ICourseInfoRepository courseInfoRepository;

    @Autowired
    private ICourseTypeRepository courseTypeRepository;

    @Autowired
        private ModelMapper modelMapper;

    @Override
    public List<Course> list() {
        List<Course> courses = courseRepository.findAll();
        return courses;
    }

    @Override
    public Course create(Course course) {
        var listInfo = courseInfoRepository.findAll();
        var listType = courseTypeRepository.findAll();
//

//        for (var checkInfo : listInfo
//        ) {
//            if (checkInfo.getInfoId().equals(course.getCourseInfo().getInfoId())) {
//                course.setCourseInfo(checkInfo);
//                break;
//            }
//
//        }
//
//        for (var checkType : listType
//        ) {
//            if (checkType.getTypeId().equals(course.getCourseType().getTypeId())) {
//                course.setCourseType(checkType);
//                break;
//            }
//
//        }

        try {
            Optional<CourseType> courseType = courseTypeRepository.findById(course.getCourseType().getTypeId());
            if (courseType.isEmpty()){
                throw new NotFoundException("course not found");
            }
            course.setCourseType(courseType.get());
            return courseRepository.save(course);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Course get(String id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new NotFoundException("Course Not Found");
        }
        return course.get();
    }

    @Override
    public void update(Course course, String id) {
        try {
            Course existingCourse = get(id);
            modelMapper.map(course, existingCourse);
            courseRepository.save(existingCourse);
        } catch (Exception e) {
            throw new NotFoundException("Update Failed Because Id Not Found");
        }

    }

    @Override
    public void delete(String id) {
        try {
            Course courseExisting = get(id);
            courseRepository.delete(courseExisting);
        } catch (Exception e) {
            throw new NotFoundException("Delete Failed Because Id Not Found");
        }
    }


    List<Course> findByTitleContains(String value) {
        List<Course> courses = courseRepository.findByTitleContains(value);
        if (courses.isEmpty()) {
            throw new NotFoundException("Course with " + value + " title is not found");
        }
        return courses;
    }

    List<Course> findByDescriptionContains(String value) {
        List<Course> courses = courseRepository.findByDescriptionContains(value);
        if (courses.isEmpty()) {
            throw new NotFoundException("Course with " + value + " title is not found");
        }
        return courses;
    }

    @Override
    public List<Course> getPagging(int size, int page, String sortBy, String direction) throws Exception {
        Integer offset = (page - 1) * size;
        return courseRepository.findPagging(size, offset, sortBy, direction);
    }

    @Override
    public List<Course> getBy(String keyword, String value) throws Exception {
        switch (keyword) {
            case "title":
                return findByTitleContains(value);
            case "description":
                return findByDescriptionContains(value);
            default:
                return courseRepository.findAll();
        }
    }

    @Override
    public Page<Course> listPage(Integer page, Integer size, String direction, String sortBy) throws Exception {
            Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
            Pageable pageable = PageRequest.of((page-1), size, sort);
            Page<Course> result = courseRepository.findAll(pageable);
            return result;

    }
}
