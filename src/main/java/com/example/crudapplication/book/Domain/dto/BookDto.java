package com.example.crudapplication.book.Domain.dto;

import com.example.crudapplication.book.Domain.model.BookId;
import com.example.crudapplication.book.Domain.model.Isbn;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDto {
    private BookId id;
    private Isbn isbn;
    private String name;
    private String authorFullName;

    @Min(1)
    private Integer stock;
    private BigDecimal price;
    private Long version;




    @JsonCreator
    public BookDto(BookId id, Isbn isbn, String name, String authorFullName, Integer stock, BigDecimal price) {
        this.id = id;
        this.isbn = isbn;
        this.name = name;
        this.authorFullName = authorFullName;
        this.stock = stock;
        this.price = price;
    }

}
