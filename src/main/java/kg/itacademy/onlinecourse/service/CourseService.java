package kg.itacademy.onlinecourse.service;

import kg.itacademy.onlinecourse.model.CourseModel;

public interface CourseService {
    CourseModel addCourse(CourseModel courseModel);

    boolean update(CourseModel courseModel);

    boolean deleteById(Long id);
}
