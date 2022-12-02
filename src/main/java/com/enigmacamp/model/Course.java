package com.enigmacamp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tbl_course")
@ToString
public class Course {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Getter
    @Setter
    String courseId;

    @Column(name = "title", nullable = false, length = 150, unique = true)
    @Getter
    @Setter
    String title;

    @Column(name = "description", nullable = false, length = 250)
    @Getter
    @Setter
    String description;

    @Column(name = "link", nullable = false, length = 200)
    @Getter
    @Setter
    String link;

    @Setter
    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "infoId", referencedColumnName = "infoId", nullable = false)
    private CourseInfo courseInfo;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "typeId", referencedColumnName = "typeId", nullable = false)
    @JsonBackReference
    private CourseType courseType;
}
