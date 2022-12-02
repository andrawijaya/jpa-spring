package com.enigmacamp.repository;

import com.enigmacamp.model.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseTypeRepository extends JpaRepository<CourseType, String> {

}
