package kg.itacademy.onlinecourse.controller;

import kg.itacademy.onlinecourse.model.LessonModel;
import kg.itacademy.onlinecourse.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/lesson")
@Slf4j
public class LessonController {

    @Autowired
    public LessonService lessonService;


    @PostMapping(path = "/add")
    public ResponseEntity<LessonModel> addNewLesson(@Valid @RequestBody LessonModel lessonModel) {
        LessonModel result = lessonService.addLesson(lessonModel);

        if (result.getId() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    @PutMapping(path = "/update")
    public ResponseEntity<Boolean> updateLesson(@RequestBody LessonModel lessonModel) {
        try {
            lessonService.update(lessonModel);
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
            return ResponseEntity.ok(lessonService.deleteById(id));
        } catch (RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
