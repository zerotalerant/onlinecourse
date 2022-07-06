package kg.itacademy.onlinecourse.controller;


import kg.itacademy.onlinecourse.repository.LessonRepository;
import kg.itacademy.onlinecourse.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/lessons")
@Slf4j
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private LessonRepository lessonRepository;



}
