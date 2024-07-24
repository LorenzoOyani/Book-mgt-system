package com.example.crudapplication.book.Domain;

import org.apache.commons.validator.routines.ISBNValidator;

public class IsbnFactory {

    private static final ISBNValidator validator=  new ISBNValidator();

    public static Isbn CreateIsbn(String isbnValue){
        if(isbnValue==null || !validator.isValid(isbnValue)){
            throw new IllegalArgumentException();
        }

        return new Isbn(isbnValue);
    }
}
