package kg.itacademy.onlinecourse.service;

import kg.itacademy.onlinecourse.model.LessonModel;

import java.util.List;

public interface LessonService {
    LessonModel create( LessonModel lessonModel);

    LessonModel update( LessonModel lessonModel);

    String deleteById( Long id);

    LessonModel getById(Long id);

    List<LessonModel> getAll();

}
