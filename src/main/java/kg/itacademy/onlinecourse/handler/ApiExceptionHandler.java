package kg.itacademy.onlinecourse.handler;

import kg.itacademy.onlinecourse.exceptions.ErrorException;
import kg.itacademy.onlinecourse.model.ErrorModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> handleValidationExceptions ( RuntimeException ex )
    {
        return ResponseEntity.badRequest ().body ( ex.getMessage () );
    }

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorModel> handleValidationExceptions ( ErrorException ex )
    {
        ErrorModel errorModel = new ErrorModel ();
        errorModel.setMessage ( ex.getMessage () );
        errorModel.setClassName ( ErrorException.class.toString () );
        return ResponseEntity.status ( ex.getStatus () ).body ( errorModel );
    }
}
