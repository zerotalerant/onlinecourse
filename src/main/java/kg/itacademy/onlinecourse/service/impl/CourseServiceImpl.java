package kg.itacademy.onlinecourse.service.impl;

import kg.itacademy.onlinecourse.entity.CourseEntity;
import kg.itacademy.onlinecourse.exceptions.CourseNotFoundException;
import kg.itacademy.onlinecourse.exceptions.FieldCantBeNullException;
import kg.itacademy.onlinecourse.mapper.CourseMapper;
import kg.itacademy.onlinecourse.model.CourseModel;
import kg.itacademy.onlinecourse.repository.CourseRepository;
import kg.itacademy.onlinecourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public CourseModel create ( CourseModel courseModel )
    {
        if ( courseModel == null )
        {
            throw new FieldCantBeNullException ( "Field is null, check one more time" );
        }
        CourseEntity courseEntity = CourseMapper.INSTANCE.toEntity ( courseModel );
        courseEntity = courseRepository.save ( courseEntity );
        return CourseMapper.INSTANCE.toModel ( courseEntity );
    }

    @Override
    public CourseModel update ( CourseModel courseModel )
    {
        if ( courseModel == null )
        {
            throw new FieldCantBeNullException ( "Field is null, check one more time" );
        }
        CourseEntity existCourse = courseRepository.findById ( courseModel.getId () )
                .orElseThrow ( () -> new CourseNotFoundException ( "Course not found by id: " + courseModel.getId () ) );

        return CourseMapper.INSTANCE.toModel ( existCourse );
    }


    @Override
    public boolean deleteById ( Long id )
    {
        courseRepository.deleteById ( id );
        return true;
    }

    @Override
    public CourseModel getById ( Long id )
    {
        CourseEntity existEntity = courseRepository.findById ( id )
                .orElseThrow ( () -> new CourseNotFoundException ( "Course not found by id: " + id ) );

        return CourseMapper.INSTANCE.toModel ( existEntity );
    }

    @Override
    public List<CourseModel> getAll ()
    {
        List<CourseEntity> existEntityList = courseRepository.findAll ();

        return CourseMapper.INSTANCE.toListModel ( existEntityList );
    }
}
