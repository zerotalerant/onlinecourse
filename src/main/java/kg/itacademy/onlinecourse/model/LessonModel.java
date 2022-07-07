package kg.itacademy.onlinecourse.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NotNull
public class LessonModel {
    @NotNull
    private Long id;

    @NotBlank
    private String lessonName;
    private String lessonInfo;
}
