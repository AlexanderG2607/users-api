package com.globalogic.bci.usersapi.controller.advice;

import com.globalogic.bci.usersapi.dto.ErrorDetailResponseDTO;
import com.globalogic.bci.usersapi.dto.ErrorResponseDTO;
import com.globalogic.bci.usersapi.exception.UserAlreadyExistsException;
import com.globalogic.bci.usersapi.exception.UserUnauthorizedException;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

@ControllerAdvice
public class UsersControllerAdvice {


    @ExceptionHandler( MethodArgumentNotValidException.class )
    protected ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, WebRequest request) {
        val field = ex.getBindingResult().getFieldError().getField();
        val message = ex.getBindingResult().getFieldError().getDefaultMessage();
        return new ResponseEntity<>(
                new ErrorResponseDTO(
                        Arrays.asList(new ErrorDetailResponseDTO(Timestamp.valueOf(LocalDateTime.now()),
                                1, "error in field: " + field + "; message: "+ message))), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        String errorMessage = ex.getMessage();

        // Puedes construir un DTO de error personalizado si lo deseas
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setErrors(Arrays.asList(new ErrorDetailResponseDTO(
                Timestamp.valueOf(LocalDateTime.now()), 2, errorMessage)));

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserUnauthorizedException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException(UserUnauthorizedException ex) {
        String errorMessage = ex.getMessage();

        // Puedes construir un DTO de error personalizado si lo deseas
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setErrors(Arrays.asList(new ErrorDetailResponseDTO(
                Timestamp.valueOf(LocalDateTime.now()), 2, errorMessage)));

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

}
