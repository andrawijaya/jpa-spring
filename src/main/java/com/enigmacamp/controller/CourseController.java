package com.enigmacamp.controller;

import com.enigmacamp.model.Course;
import com.enigmacamp.model.request.CourseRequest;

import com.enigmacamp.model.response.PaggingResponse;
import com.enigmacamp.model.response.SuccessResponse;
import com.enigmacamp.repository.ICourseRepository;
import com.enigmacamp.service.CourseArrayService;
import com.enigmacamp.service.ICourseService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Validated
@RestController
@RequestMapping("/courses")
public class CourseController {
    // aturannya penamaan urlnya dengan nama benda

    @Autowired
    private ICourseService courseService;

    @Autowired
        private ModelMapper modelMapper;

    @Autowired
    private ICourseRepository repository;

    @GetMapping("/paging")
    public ResponseEntity getAllPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size, @RequestParam(defaultValue = "DESC") String direction, @RequestParam(defaultValue = "courseId") String sortBy) throws Exception{
        Page<Course> courses = courseService.listPage(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PaggingResponse<>("Success Get course list", courses));
    }

    @GetMapping
    public ResponseEntity getAllCourse() throws Exception {
        List<Course> couses = courseService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Succes Get All Course", couses));
//        try{
//            List<Course> couses = courseService.list();
//            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Succes Get All Course", couses));
//        }catch(Exception e){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01" ,e.getMessage()));
//        }
    }

    @PostMapping
    public ResponseEntity createCourse(@RequestBody CourseRequest course) throws Exception {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Course newCourse = modelMapper.map(course, Course.class);

        Course result = courseService.create(newCourse);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success Created Course", result));
//        try{
//            Course newCourse = modelMapper.map(course, Course.class);
//            Course result = courseService.create(newCourse);
//            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success Created Course", result));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X02",e.getMessage()));
//        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id) throws Exception {
        var courses = courseService.get(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(new SuccessResponse<>("Success Get Course By id", courses));
//        try{
//            var courses =  courseService.get(id);
//            return ResponseEntity.status(HttpStatus.FOUND).body(new SuccessResponse<>("Success Get Course By id", courses));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01",e.getMessage()));
//        }
    }

    @GetMapping(params = {"keyword", "value"})
    public ResponseEntity getBy(@RequestParam String keyword, @RequestParam String value) throws Exception {
        List<Course> result = courseService.getBy(keyword, value);
        return ResponseEntity.status(HttpStatus.FOUND).body(new SuccessResponse<>("Success Get Course By key", result));
//        try{
//            List<Course> result =  courseService.getBy(keyword, value);
//            if(result.isEmpty()){
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("404","Course not found"));
//            }
//            return ResponseEntity.status(HttpStatus.FOUND).body(new SuccessResponse<>("Success Get Course By key", result));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("500","Internal Server Error"));
//        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteId(@PathVariable("id") String id) throws Exception {
        courseService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Data Has Been Deleted !");
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@Valid @RequestBody CourseRequest course, @PathVariable("id") String id) throws Exception {
        Course newCourse = modelMapper.map(course, Course.class);
        newCourse.setCourseId(id);
        courseService.update(newCourse, id);
        return ResponseEntity.status(HttpStatus.OK).body("Data Has Updated !");
    }

    @GetMapping("/page")
    public ResponseEntity getPagging(@RequestParam int page, @RequestParam int size , @RequestParam String sortBy, @RequestParam String direction) throws Exception {
        List<Course> couses = courseService.getPagging(size, page, sortBy, direction);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Succes", couses));

    }




}
