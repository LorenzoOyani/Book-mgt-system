package com.example.crudapplication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

@ControllerAdvice
public class ControllerException {


    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ErrorResponse> handleException(GeneralException ex, WebRequest request){

        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
        HttpStatusCode statusCode = mappedToHttpStatus(errorCode);
        return new ResponseEntity<>(errorResponse, statusCode);


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
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
