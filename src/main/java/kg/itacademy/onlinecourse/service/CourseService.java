package kg.itacademy.onlinecourse.service;

import kg.itacademy.onlinecourse.model.CourseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    CourseModel create ( CourseModel courseModel );

    CourseModel update ( CourseModel courseModel );

    boolean deleteById ( Long id );

    CourseModel getById ( Long id );

    List<CourseModel> getAll ( );

}
