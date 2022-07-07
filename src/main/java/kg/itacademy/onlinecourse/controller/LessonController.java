package kg.itacademy.onlinecourse.controller;


import kg.itacademy.onlinecourse.exceptions.CanNotUpdateException;
import kg.itacademy.onlinecourse.exceptions.CourseNotFoundException;
import kg.itacademy.onlinecourse.exceptions.LessonNotFoundException;
import kg.itacademy.onlinecourse.model.LessonModel;
import kg.itacademy.onlinecourse.repository.LessonRepository;
import kg.itacademy.onlinecourse.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.sql.ResultSet;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/lessons")
@Slf4j
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private LessonRepository lessonRepository;

    @PostMapping(path = "/add")
    public ResponseEntity<LessonModel> addCourse ( @NotBlank @RequestBody LessonModel lessonModel )
    {
        LessonModel lessonCreated = lessonService.create ( lessonModel );
        if ( lessonCreated.getId () != null )
        {
            return ResponseEntity.status ( HttpStatus.CREATED ).body ( lessonCreated );
        } else
        {
            return ResponseEntity.status ( HttpStatus.INTERNAL_SERVER_ERROR ).body ( null );
        }
    }

    @DeleteMapping(path = "/delete/{lessonId}")
    public ResponseEntity<Boolean> deleteLessonById ( @NotBlank @PathVariable("lessonId") Long lessonId )
    {
        try
        {
            return ResponseEntity.ok ( lessonService.deleteById ( lessonId ) );
        } catch (CourseNotFoundException e)
        {
            log.error ( e.getMessage (), e );
            return ResponseEntity.status ( HttpStatus.INTERNAL_SERVER_ERROR ).body ( null );
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Boolean> updateLesson ( @NotBlank @RequestBody LessonModel lessonModel )
    {
        try
        {
            lessonService.update ( lessonModel );
            return ResponseEntity.ok ( true );
        } catch (CanNotUpdateException e)
        {
            log.error ( e.getMessage (), e );
            return ResponseEntity.status ( HttpStatus.INTERNAL_SERVER_ERROR ).body ( null );
        }
    }

    @GetMapping(path = "/get/{lessonId}")
    public ResponseEntity<LessonModel> getLessonById ( @NotBlank @PathVariable("lessonId") Long lessonId )
    {
        try
        {
            return ResponseEntity.ok ( lessonService.getById ( lessonId ) );
        } catch (CourseNotFoundException e)
        {
            log.error ( e.getMessage (), e );
            return ResponseEntity.status ( HttpStatus.INTERNAL_SERVER_ERROR ).body ( null );
        }
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<List<LessonModel>> getAll ()
    {
        try
        {
            return ResponseEntity.ok ( lessonService.getAll () );
        } catch (LessonNotFoundException e)
        {
            log.error ( e.getMessage (), e );
            return ResponseEntity.status ( HttpStatus.INTERNAL_SERVER_ERROR ).body ( null );
        }

    }
}