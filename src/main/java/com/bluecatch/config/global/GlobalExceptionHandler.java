package com.bluecatch.config.global;

import com.bluecatch.data.dto.response.ExceptionResponse;
import com.bluecatch.utils.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.xml.bind.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@SuppressWarnings("unused")
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> exception(NotFoundException ex){
        ExceptionResponse response = ExceptionResponse.builder()
                .message(ex.getMessage())
                .code(BAD_REQUEST.toString())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionResponse> exception(NoSuchElementException ex){
        ExceptionResponse response = ExceptionResponse.builder()
                .message(ex.getMessage())
                .code(BAD_REQUEST.toString())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> exception(MethodArgumentTypeMismatchException ex){
        ExceptionResponse response = ExceptionResponse.builder()
                .message(ex.getMessage())
                .code(BAD_REQUEST.toString())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponse> exception(ValidationException ex){
        ExceptionResponse response = ExceptionResponse.builder()
                .message(ex.getMessage())
                .code(BAD_REQUEST.toString())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        String errorMessage = errors.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));

        ExceptionResponse response = ExceptionResponse.builder()
                .message("Validation failed: " + errorMessage)
                .code(BAD_REQUEST.toString())
                .build();

        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));

        ExceptionResponse response = ExceptionResponse.builder()
                .message("Validation failed: " + errorMessage)
                .code(BAD_REQUEST.toString())
                .build();

        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exception(Exception ex){
        ExceptionResponse response = ExceptionResponse.builder()
                .message(ex.getMessage())
                .code(BAD_REQUEST.toString())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }
}