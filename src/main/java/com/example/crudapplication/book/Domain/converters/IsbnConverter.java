package com.example.crudapplication.book.Domain.converters;

import com.example.crudapplication.book.Domain.Isbn;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class IsbnConverter implements AttributeConverter<Isbn, String> {


    @Override
    public String convertToDatabaseColumn(Isbn isbn) {
        return isbn != null ? isbn.getValue() : null;
    }

    @Override
    public Isbn convertToEntityAttribute(String value) {
        return value != null ? new Isbn(value) : null;
    }
}
