package com.enigmacamp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_courseType")
@ToString
public class CourseType {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Getter
    @Setter
    private String typeId;

    @Column(name = "typeName", nullable = false, length = 50, unique = true)
    @Getter
    @Setter
    private String typeName;

    @OneToMany(mappedBy = "courseType",fetch = FetchType.LAZY)
    @Getter
    @Setter
    @JsonManagedReference
    private Set<Course> courseList = new HashSet<Course>();
}
