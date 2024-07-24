package com.example.crudapplication.book.Domain;

import com.example.crudapplication.author.Domain.Author;
import com.example.crudapplication.author.Domain.AuthorId;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class BookDto {
    private BookId id;
    private Isbn isbn;
    private String name;
    private String authorFullName;
    private Integer stock;
    private BigDecimal price;
    private AuthorId author;

    // No-argument constructor
    public BookDto() {}

    // Constructor with all fields
    @JsonCreator
    public BookDto(BookId id, Isbn isbn, String name, String authorFullName, Integer stock, BigDecimal price, AuthorId author) {
        this.id = id;
        this.isbn = isbn;
        this.name = name;
        this.authorFullName = authorFullName;
        this.stock = stock;
        this.price = price;
        this.author = author;
    }

    // Getters and setters...
}
