package com.example.crudapplication.Exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    BOOK_NOT_FOUND("BOOK_NOT_FOUND", "book not found"),
    RECORD_NOT_FOUND("RECORD_NOT_FOUND","record not found"),
    AUTHOR_NOT_FOUND("AUTHOR_NOT_FOUND", "book with this author name not found");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
