package com.enigmacamp.repository;

import com.enigmacamp.model.Course;
import com.enigmacamp.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseArrayRepository implements ICourseArrayRepository {


    @Autowired
    RandomStringGenerator randomStringGenerator;

    List<Course> courseList = new ArrayList<>();
//    {
//        Course course = new Course();
//        course.setCourseId("1");
//        course.setTitle("title");
//        course.setDescription("desc");
//        course.setLink("link");
//
//        courseList.add(course);
//    }


    @Override
    public List<Course> getAll() throws Exception {
        return courseList;
    }

    @Override
    public Course create(Course course) throws Exception {
        course.setCourseId(randomStringGenerator.random());
        courseList.add(course);
        return course;
    }

    @Override
    public Optional<Course> findById(String id) throws Exception {
        for (Course course: courseList
             ) {
            if(course.getCourseId().equals(id)){
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Course course, String id) throws Exception {
        for (Course courseU: courseList
             ) {
            if(courseU.getCourseId().equals(id)){
                courseU.setTitle(course.getTitle());
                courseU.setDescription(course.getDescription());
                courseU.setLink(course.getLink());
                break;
            }
        }
    }

    @Override
    public void delete(String id) throws Exception {
        for (Course courseD: courseList
             ) {
            if(courseD.getCourseId().equals(id)){
                courseList.remove(courseD);
                break;
            }
        }
    }

    @Override
    public List<Course> getBy(String keyword, String value) {
        List<Course> newArr = new ArrayList<>();
        switch (keyword.toLowerCase()){
            case "courseid":
                for (Course courseId: courseList
                     ) {
                    if (courseId.getCourseId().contains(value.toLowerCase())){
                        newArr.add(courseId);
                    }
                }
                break;
            case "title":
                for (Course courseTitle:courseList
                     ) {
                    if (courseTitle.getTitle().contains(value.toLowerCase())){
                        newArr.add(courseTitle);
                    }
                }
                break;
            case "description":
                for (Course courseDesc: courseList
                     ) {
                    if(courseDesc.getDescription().contains(value.toLowerCase())){
                        newArr.add(courseDesc);
                    }
                }
                break;
            case "link":
                for (Course courseLink: courseList
                     ) {
                    if (courseLink.getLink().contains(value.toLowerCase())){
                        newArr.add(courseLink);
                    }
                }
                break;
            default:
                break;
        }
        return newArr;
    }
}
