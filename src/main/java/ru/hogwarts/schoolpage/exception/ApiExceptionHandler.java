package ru.hogwarts.schoolpage.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {StudentNotFoundException.class, AvatarNotFoundException.class})
    public ResponseEntity<?> handleNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = AvatarProcessException.class)
    public ResponseEntity<?> handleAvatarProcessException() {
        return ResponseEntity.badRequest().build();
    }
}
