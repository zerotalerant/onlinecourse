package kg.itacademy.onlinecourse.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException ( String message )
    {
        super ( message );
    }
}
