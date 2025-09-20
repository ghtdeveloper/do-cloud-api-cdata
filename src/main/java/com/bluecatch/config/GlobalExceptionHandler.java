package com.bluecatch.config;

import com.bluecatch.data.dto.response.ExceptionResponse;
import com.bluecatch.utils.NotFoundException;
import jakarta.xml.bind.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.util.NoSuchElementException;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exception(Exception ex){
        ExceptionResponse response = ExceptionResponse.builder()
                .message(ex.getMessage())
                .code(BAD_REQUEST.toString())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

}