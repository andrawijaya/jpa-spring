package com.enigmacamp.repository;


import com.enigmacamp.model.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseInfoRepository extends JpaRepository<CourseInfo, String> {

}
