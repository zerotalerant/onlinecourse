package kg.itacademy.onlinecourse.service.impl;

import kg.itacademy.onlinecourse.entity.CourseEntity;
import kg.itacademy.onlinecourse.exceptions.CourseNotFoundException;
import kg.itacademy.onlinecourse.exceptions.FieldCantBeNullException;
import kg.itacademy.onlinecourse.mapper.CourseMapper;
import kg.itacademy.onlinecourse.model.CourseModel;
import kg.itacademy.onlinecourse.repository.CourseRepository;
import kg.itacademy.onlinecourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private final CourseRepository courseRepository;

    public CourseServiceImpl ( CourseRepository courseRepository )
    {
        this.courseRepository = courseRepository;
    }

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

        existCourse.setCourseName ( courseModel.getCourseName () );
        existCourse.setCourseInfo ( courseModel.getCourseInfo () );

        existCourse = courseRepository.save ( existCourse );

        return CourseMapper.INSTANCE.toModel ( existCourse );
    }


    @Override
    public String deleteById ( Long id )
    {
        courseRepository.deleteById ( id );
        return "Course successfully deleted!";

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
