package com.enigmacamp.controller;

import com.enigmacamp.model.CourseInfo;
import com.enigmacamp.model.CourseType;
import com.enigmacamp.model.request.CourseInfoRequest;
import com.enigmacamp.model.request.CourseTypeRequest;
import com.enigmacamp.model.response.SuccessResponse;
import com.enigmacamp.service.ICourseInfoService;
import com.enigmacamp.service.ICourseTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/courseType")
public class CourseTypeController {
    @Autowired
    private ICourseTypeService courseTypeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAllCourseType() throws Exception {
        List<CourseType> courseTypes = courseTypeService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Succes Get All Course Type", courseTypes));
    }

    @PostMapping
    public ResponseEntity createCourseType(@RequestBody CourseTypeRequest courseTypeRequest) throws Exception {
        CourseType newCourseType = modelMapper.map(courseTypeRequest, CourseType.class);
        CourseType result = courseTypeService.create(newCourseType);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success Created Course", result));
    }
}
