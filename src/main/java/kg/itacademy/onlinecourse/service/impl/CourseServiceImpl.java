package kg.itacademy.onlinecourse.service.impl;

import kg.itacademy.onlinecourse.entity.CourseEntity;
import kg.itacademy.onlinecourse.exceptions.CourseNotFoundException;
import kg.itacademy.onlinecourse.model.CourseModel;
import kg.itacademy.onlinecourse.repository.CourseRepository;
import kg.itacademy.onlinecourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;


@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public CourseModel addCourse(CourseModel courseModel) {

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseName(courseModel.getCourseName());
        courseEntity.setCourseInfo(courseModel.getCourseInfo());
        courseEntity = courseRepository.save(courseEntity);

        courseModel.setId(courseEntity.getId());

        return courseModel;
    }


    @Override
    public boolean update(CourseModel courseModel) {
        CourseEntity existCourse = courseRepository.getById(courseModel.getId());
        if (existCourse == null) {
            throw new CourseNotFoundException("course not found by id " + courseModel.getId());
        }

        existCourse.setCourseName(courseModel.getCourseName());
        existCourse.setCourseInfo(courseModel.getCourseInfo());


        existCourse = courseRepository.save(existCourse);

        return existCourse.getId() != null;
    }

    @Override
    public boolean deleteById(Long id) {
        courseRepository.deleteById(id);

        return true;
    }
}
