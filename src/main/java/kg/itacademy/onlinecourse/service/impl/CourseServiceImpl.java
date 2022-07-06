package kg.itacademy.onlinecourse.service.impl;

import kg.itacademy.onlinecourse.entity.CourseEntity;
import kg.itacademy.onlinecourse.model.CourseModel;
import kg.itacademy.onlinecourse.repository.CourseRepository;
import kg.itacademy.onlinecourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

}
