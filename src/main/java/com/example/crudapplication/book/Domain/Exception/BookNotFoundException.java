package com.example.crudapplication.book.Domain.Exception;

import com.example.crudapplication.book.Domain.model.BookId;

import java.util.Objects;

public class BookNotFoundException extends RuntimeException{

    private final BookId bookId;
    private static final String MESSAGE_DEFAULT = "error getting book with id:  ";
    public BookNotFoundException(BookId bookId){
        super(MESSAGE_DEFAULT.concat(String.valueOf(bookId)));
        Objects.requireNonNull(bookId);
        this.bookId = bookId;
    }
}
