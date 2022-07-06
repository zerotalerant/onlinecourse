package kg.itacademy.onlinecourse.controller;

import kg.itacademy.onlinecourse.exceptions.CanNotUpdateException;
import kg.itacademy.onlinecourse.exceptions.CourseNotFoundException;
import kg.itacademy.onlinecourse.model.CourseModel;
import kg.itacademy.onlinecourse.repository.CourseRepository;
import kg.itacademy.onlinecourse.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/courses")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping(path = "/add")
    public ResponseEntity<CourseModel> addCourse ( @RequestBody CourseModel courseModel )
    {
        CourseModel courseCreated = courseService.create ( courseModel );
        if ( courseCreated.getId () != null )
        {
            return ResponseEntity.status ( HttpStatus.CREATED ).body ( courseCreated );
        } else
        {
            return ResponseEntity.status ( HttpStatus.INTERNAL_SERVER_ERROR ).body ( null );
        }
    }

    @DeleteMapping(path = "/delete/{courseId}")
    public ResponseEntity<Boolean> deleteCourseById ( @PathVariable("courseId") Long courseId )
    {
        try
        {
            return ResponseEntity.ok ( courseService.deleteById ( courseId ) );
        } catch (CourseNotFoundException e)
        {
            log.error ( e.getMessage (), e );
            return ResponseEntity.status ( HttpStatus.INTERNAL_SERVER_ERROR ).body ( null );
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Boolean> updateCourse ( @RequestBody CourseModel courseModel )
    {
        try
        {
            courseService.update ( courseModel );
            return ResponseEntity.ok ( true );
        } catch (CanNotUpdateException e)
        {
            log.error ( e.getMessage (), e );
            return ResponseEntity.status ( HttpStatus.INTERNAL_SERVER_ERROR ).body ( null );
        }
    }

    @GetMapping(path = "/get/{courseId}")
    public ResponseEntity<CourseModel> getCourseById ( @PathVariable("courseId") Long courseId )
    {
        try
        {
            return ResponseEntity.ok ( courseService.getById ( courseId ) );
        } catch (CourseNotFoundException e)
        {
            log.error ( e.getMessage (), e );
            return ResponseEntity.status ( HttpStatus.INTERNAL_SERVER_ERROR ).body ( null );
        }
    }


}
