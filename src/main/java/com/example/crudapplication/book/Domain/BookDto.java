package com.example.crudapplication.book.Domain;

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

    // No-argument constructor
    public BookDto() {}

    // Constructor with all fields
    public BookDto(BookId id, Isbn isbn, String name, String authorFullName, Integer stock, BigDecimal price) {
        this.id = id;
        this.isbn = isbn;
        this.name = name;
        this.authorFullName = authorFullName;
        this.stock = stock;
        this.price = price;
    }

    // Getters and setters...
}
