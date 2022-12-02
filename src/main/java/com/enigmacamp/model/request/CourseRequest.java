package com.enigmacamp.model.request;

import com.enigmacamp.model.CourseType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
public class CourseRequest {
    @NotBlank(message = "title is required")
    @Getter @Setter
    String title;

    @Getter @Setter
    String description;

    @NotBlank(message = "link is required")
    @Getter @Setter
    String link;

    @Getter @Setter
    private CourseInfoRequest courseInfo;

    @Getter @Setter
    private CourseTypeIdRequest courseType;

}
