package kg.itacademy.onlinecourse.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NotNull
public class CourseModel {
    @NotNull
    private Long id;

    @NotBlank
    private String courseName;
    private String courseInfo;
}
