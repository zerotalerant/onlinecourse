package kg.itacademy.onlinecourse.service;

import kg.itacademy.onlinecourse.model.CourseModel;

import java.util.List;


public interface CourseService {
    CourseModel create ( CourseModel courseModel );

    CourseModel update ( CourseModel courseModel );

    String deleteById ( Long id );

    CourseModel getById ( Long id );

    List<CourseModel> getAll ( );

}
