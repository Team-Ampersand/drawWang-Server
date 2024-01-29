package server.drawwang.global.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.drawwang.global.exception.ThreadNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ThreadNotFoundException.class)
    public ResponseEntity<HttpStatus> handleMethodThreadNotFoundException() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
