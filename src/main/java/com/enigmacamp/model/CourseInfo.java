package com.enigmacamp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tbl_info")
@ToString
public class CourseInfo {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Getter @Setter
    String infoId;

    @Getter @Setter
    @Column(name = "duration", nullable = false, length = 50)
    String duration;

    @Getter @Setter
    @Column(name = "level", nullable = false, length = 50)
    String level;

}
