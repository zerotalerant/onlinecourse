package kg.itacademy.onlinecourse.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NotNull(message = "Create course model is null")
public class CourseModel {
    private Long id;

    @NotBlank(message = "course name can't be blank")
    private String courseName;
    private String courseInfo;
}
