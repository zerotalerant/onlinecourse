package kg.itacademy.onlinecourse.controller;

import kg.itacademy.onlinecourse.model.LessonModel;
import kg.itacademy.onlinecourse.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/lesson")
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
}
