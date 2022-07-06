package kg.itacademy.onlinecourse.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseEntity extends BaseEntity {

    @Column(name = "course_name", nullable = false, unique = true)
    String courseName;

    @Column(name = "course_info", nullable = false)
    String courseInfo;


    @OneToMany(mappedBy = "course")
    List<LessonEntity> lessons;
}

