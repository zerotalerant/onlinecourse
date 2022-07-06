package kg.itacademy.onlinecourse.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NotNull(message = "Create lesson model is null")
public class LessonModel {
    private Long id;

    @NotBlank(message = "lesson name can't be blank")
    private String lessonName;
    private String lessonInfo;
}
