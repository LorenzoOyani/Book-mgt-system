package com.example.crudapplication.Exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Slf4j
@ControllerAdvice
public class ControllerException {



    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ErrorResponse> handleException(GeneralException ex, WebRequest request){

        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
        HttpStatusCode statusCode = mappedToHttpStatus(errorCode);
        return new ResponseEntity<>(errorResponse, statusCode);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request){
        ErrorResponse  errorResponse  =  new ErrorResponse("INTERNAL SERVER ERROR",  ex.getMessage());
        return new ResponseEntity<>(Objects.requireNonNull(errorResponse), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private HttpStatusCode mappedToHttpStatus(ErrorCode errorCode) {
        if (Objects.requireNonNull(errorCode) == ErrorCode.BOOK_NOT_FOUND) {
            //   Objects.requireNonNull is used to check parameter  validation in a  method or constructor
            return HttpStatus.NOT_FOUND;
        } else if (Objects.requireNonNull(errorCode) == ErrorCode.AUTHOR_NOT_FOUND) {
            return HttpStatus.NOT_FOUND;

        }else if(Objects.requireNonNull(errorCode) == ErrorCode.PREFIX_NOT_FOUND){
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
