package kg.itacademy.onlinecourse.service.impl;

import kg.itacademy.onlinecourse.entity.LessonEntity;
import kg.itacademy.onlinecourse.exceptions.FieldCantBeNullException;
import kg.itacademy.onlinecourse.exceptions.LessonNotFoundException;
import kg.itacademy.onlinecourse.mapper.LessonMapper;
import kg.itacademy.onlinecourse.model.LessonModel;
import kg.itacademy.onlinecourse.repository.LessonRepository;
import kg.itacademy.onlinecourse.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    public LessonServiceImpl ( LessonRepository lessonRepository )
    {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public LessonModel create ( LessonModel lessonModel )
    {
        if ( lessonModel == null )
        {
            throw new FieldCantBeNullException ( "Field is null, check one more time" );
        }

        LessonEntity lessonEntity = LessonMapper.INSTANCE.toEntity ( lessonModel );
        lessonEntity = lessonRepository.save ( lessonEntity );

        return LessonMapper.INSTANCE.toModel ( lessonEntity );
    }

    @Override
    public LessonModel update ( LessonModel lessonModel )
    {
        LessonEntity existLessonEntity = lessonRepository.getById ( lessonModel.getId () );
        if ( existLessonEntity == null )
        {
            throw new LessonNotFoundException ( "Lesson not found by id " + lessonModel.getId () );
        }

        LessonEntity existLesson = lessonRepository.findById ( lessonModel.getId () )
                .orElseThrow ( () -> new LessonNotFoundException ( "Lesson not found by id: " + lessonModel.getId () ) );

        existLesson.setLessonName ( lessonModel.getLessonName () );
        existLesson.setLessonInfo ( Boolean.valueOf ( lessonModel.getLessonInfo () ) );

        existLesson = lessonRepository.save ( existLesson );

        return LessonMapper.INSTANCE.toModel ( existLesson );
    }

    @Override
    public String deleteById ( Long id )
    {
        lessonRepository.deleteById ( id );
        return "Lesson successfully deleted!";

    }

    @Override
    public LessonModel getById ( Long id )
    {
        LessonEntity existLesson = lessonRepository.findById ( id )
                .orElseThrow ( () -> new LessonNotFoundException ( "Lesson not found by id: " + id ) );

        return LessonMapper.INSTANCE.toModel ( existLesson );
    }

    @Override
    public List<LessonModel> getAll ()
    {
        List<LessonEntity> lessonEntityList = lessonRepository.findAll ();
        return LessonMapper.INSTANCE.toListModel ( lessonEntityList );
    }
}
