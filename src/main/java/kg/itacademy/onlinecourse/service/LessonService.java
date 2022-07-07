package kg.itacademy.onlinecourse.service;

import kg.itacademy.onlinecourse.model.CourseModel;
import kg.itacademy.onlinecourse.model.LessonModel;

public interface LessonService {
    LessonModel addLesson(LessonModel lessonModel);

    boolean update(LessonModel lessonModel);

    boolean deleteById(Long id);
}

