package kg.itacademy.onlinecourse.exceptions;

public class LessonNotFoundException extends RuntimeException{
    public LessonNotFoundException(String message) {
        super(message);
    }
}
