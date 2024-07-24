package com.example.crudapplication.book.Domain;

import jakarta.persistence.AttributeConverter;

public class BookIdConverter implements AttributeConverter<BookId, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BookId bookId) {
        return bookId != null? bookId.getId() : null;
    }

    @Override
    public BookId convertToEntityAttribute(Integer integer) {
        return integer != null ? new BookId(integer): null;
    }
}
