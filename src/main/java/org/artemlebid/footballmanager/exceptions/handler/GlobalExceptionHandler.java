package org.artemlebid.footballmanager.exceptions.handler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.artemlebid.footballmanager.dtos.responses.ErrorResponseDto;
import org.artemlebid.footballmanager.exceptions.NotAllowedOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorResponseDto error = new ErrorResponseDto();
        error.setError(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(MethodArgumentNotValidException ex) {
        ErrorResponseDto error = new ErrorResponseDto();
        error.setError(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(ConstraintViolationException ex) {
        ErrorResponseDto error = new ErrorResponseDto();
        error.setError(ex.getConstraintViolations().iterator().next().getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAllowedOperation.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(NotAllowedOperation ex) {
        ErrorResponseDto error = new ErrorResponseDto();
        error.setError(ex.getMessage());
        error.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());

        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(HttpRequestMethodNotSupportedException ex) {
        ErrorResponseDto error = new ErrorResponseDto();
        error.setError(ex.getMessage());
        error.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());

        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
