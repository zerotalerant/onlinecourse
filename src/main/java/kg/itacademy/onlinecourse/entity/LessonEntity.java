package kg.itacademy.onlinecourse.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

}
