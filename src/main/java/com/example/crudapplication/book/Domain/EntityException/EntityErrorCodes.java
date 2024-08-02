package com.example.crudapplication.book.Domain.EntityException;

import lombok.Getter;

@Getter
public enum EntityErrorCodes {

    ENTITY_NOT_FOUND("ENTITY_NOT_FOUND", "Failure to find entity in dataBase"),
    SAVE_BOOK_ERROR("SAVE_BOOK_ERROR", "Failure to save book in dataBase"),
    NO_AVAILABLE_STOCK_ERROR("NO_AVAILABLE_STOCK_ERROR", "no available stock error");



    private final String code;
    private final String message;

    EntityErrorCodes(String code, String message){
        this.code=code;
        this.message=message;

    }
}
