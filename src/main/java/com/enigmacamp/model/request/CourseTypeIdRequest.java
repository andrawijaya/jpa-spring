package com.enigmacamp.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class    CourseTypeIdRequest {
    @NotBlank(message = "typeName is required")
    @Getter
    @Setter
    private  String typeId;
}
