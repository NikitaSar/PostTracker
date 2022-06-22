package PostTracker.handlers;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({NoSuchElementException.class, EmptyResultDataAccessException.class, EntityNotFoundException.class})
    public ResponseEntity<Object> handleNoSuchElementException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
