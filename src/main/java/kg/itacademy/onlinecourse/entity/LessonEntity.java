package kg.itacademy.onlinecourse.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonEntity extends BaseEntity {

    @Column(name = "lesson_name", nullable = false, unique = true)
    String lessonName;

    @Column(name = "lesson_info", nullable = false)
    Boolean lessonInfo;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    CourseEntity course;

}
