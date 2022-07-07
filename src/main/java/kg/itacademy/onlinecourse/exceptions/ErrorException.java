package kg.itacademy.onlinecourse.exceptions;

import org.springframework.http.HttpStatus;


public class ErrorException extends RuntimeException {
    private HttpStatus httpStatus;

    public ErrorException ( String message )
    {
        super ( message );
    }

    public HttpStatus getStatus ()
    {
        return HttpStatus.I_AM_A_TEAPOT;
    }
}
