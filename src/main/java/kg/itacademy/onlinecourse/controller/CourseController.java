package kg.itacademy.onlinecourse.controller;

import kg.itacademy.onlinecourse.model.CourseModel;
import kg.itacademy.onlinecourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping(path = "/add")
    public ResponseEntity<CourseModel> addNewCourse(@Valid @RequestBody CourseModel courseModel) {
        CourseModel result = courseService.addCourse(courseModel);

        if (result.getId() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }


    }
}
