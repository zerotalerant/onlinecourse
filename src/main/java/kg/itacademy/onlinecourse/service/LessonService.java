package kg.itacademy.onlinecourse.service;

import kg.itacademy.onlinecourse.model.LessonModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LessonService {
    LessonModel create( LessonModel lessonModel);

    LessonModel update( LessonModel lessonModel);

    boolean deleteById(Long id);

    LessonModel getById(Long id);

    List<LessonModel> getAll();

}
