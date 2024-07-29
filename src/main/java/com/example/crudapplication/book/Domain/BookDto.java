package com.example.crudapplication.book.Domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
public class BookDto {
    private BookId id;
    private Isbn isbn;
    private String name;
    private String authorFullName;
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
