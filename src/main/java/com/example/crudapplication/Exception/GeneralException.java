package com.example.crudapplication.Exception;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private  final  ErrorCode errorCode;

    public GeneralException(ErrorCode errorCode) {
        super(errorCode.getCode(), new Throwable(errorCode.getMessage()));
        this.errorCode = errorCode;
    }

//    public GeneralException(String responseCode, String responseMessage){
//        super(responseCode, new Throwable(responseMessage));
//    }

}
