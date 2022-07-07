package kg.itacademy.onlinecourse.controller;

import kg.itacademy.onlinecourse.model.CourseModel;
import kg.itacademy.onlinecourse.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/course")
@Slf4j
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

    @PutMapping(path = "/update")
    public ResponseEntity<Boolean> updateCourse(@RequestBody CourseModel courseModel) {
        try {
            courseService.update(courseModel);
            return ResponseEntity.ok(true);
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping(path = "/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(courseService.deleteById(id));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}

