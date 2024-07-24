package com.example.crudapplication.book.Domain.EntityException;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException{

    private final EntityErrorCodes codes;

    public EntityNotFoundException(EntityErrorCodes codes){
        super(codes.getCode(),  new Throwable(codes.getMessage()));
        this.codes=codes;
    }


}
