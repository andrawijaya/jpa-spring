package com.enigmacamp.repository;

import com.enigmacamp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICourseRepository extends JpaRepository<Course, String> {

    @Query("select c from Course c where c.title like %?1%")
    List<Course> findByTitleContains(String title);

    @Query("select c from Course c where c.description like %?1%")
    List<Course> findByDescriptionContains(String description);

    @Query(value = "select * from tbl_course ORDER BY title desc LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<Course> findPagging(Integer size, Integer offset, String sortby, String direction);




    //buat method findBywith pagination menerima (page, size, sortBy, direction)
    //untuk memnentukan pengambilan dan output pada response list course

}
