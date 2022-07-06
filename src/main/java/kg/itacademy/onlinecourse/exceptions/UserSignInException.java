package kg.itacademy.onlinecourse.exceptions;

import org.springframework.http.HttpStatus;

public class UserSignInException extends RuntimeException {
    public UserSignInException ( String message, HttpStatus unauthorized )
    {
        super ( message );
    }
}
