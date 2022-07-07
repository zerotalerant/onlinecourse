package kg.itacademy.onlinecourse.service.impl;

import kg.itacademy.onlinecourse.entity.LessonEntity;
import kg.itacademy.onlinecourse.exceptions.LessonNotFoundException;
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

    @Override
    public boolean update(LessonModel lessonModel) {
        LessonEntity existLesson = lessonRepository.getById(lessonModel.getId());
        if (existLesson == null) {
            throw new LessonNotFoundException("course not found by id " + lessonModel.getId());
        }

        lessonModel.setLessonName(lessonModel.getLessonName());
        lessonModel.setLessonInfo(lessonModel.getLessonInfo());


        existLesson = lessonRepository.save(existLesson);

        return existLesson.getId() != null;
    }

    @Override
    public boolean deleteById(Long id) {
        lessonRepository.deleteById(id);

        return true;
    }
}


