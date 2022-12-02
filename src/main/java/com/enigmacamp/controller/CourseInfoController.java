package com.enigmacamp.controller;

import com.enigmacamp.model.Course;
import com.enigmacamp.model.CourseInfo;
import com.enigmacamp.model.request.CourseInfoRequest;
import com.enigmacamp.model.request.CourseRequest;
import com.enigmacamp.model.response.SuccessResponse;
import com.enigmacamp.service.ICourseInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/courseInfo")
public class CourseInfoController {
    @Autowired
    private ICourseInfoService courseInfoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAllCourseInfo() throws Exception {
        List<CourseInfo> courseInfos = courseInfoService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Succes Get All Course Info", courseInfos));
    }

    @PostMapping
    public ResponseEntity createCourseInfo(@RequestBody CourseInfoRequest courseInfoRequest) throws Exception {
        System.out.println("ada"+courseInfoRequest);
        CourseInfo map = modelMapper.map(courseInfoRequest, CourseInfo.class);
        System.out.println("tes"+map);
        CourseInfo result = courseInfoService.create(map);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success Created Info", result));
    }
}
