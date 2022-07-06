package kg.itacademy.onlinecourse.service.impl;

import kg.itacademy.onlinecourse.entity.LessonEntity;
import kg.itacademy.onlinecourse.model.LessonModel;
import kg.itacademy.onlinecourse.repository.LessonRepository;
import kg.itacademy.onlinecourse.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public LessonModel addLesson(LessonModel lessonModel) {

        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setLessonName(lessonModel.getLessonName());
        lessonEntity.setLessonInfo(lessonModel.getLessonInfo());
        lessonEntity = lessonRepository.save(lessonEntity);

        lessonModel.setId(lessonEntity.getId());

        return lessonModel;

    }
}