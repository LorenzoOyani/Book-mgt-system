package com.example.crudapplication.book.Domain.model;

import com.example.crudapplication.book.Domain.converters.IsbnConverter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.util.Assert;

import java.math.BigDecimal;


@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Cacheable
@EqualsAndHashCode
@Builder
@Table(name = "book")
public class Book {


    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BookId id;

    @Column(name = "Isbn", unique = true)
    @NotBlank(message = "Isbn cannot be blank")

    @AttributeOverride(name = "value", column = @Column(name = "Isbn", unique = true))
    @Convert(converter = IsbnConverter.class)
    private Isbn isbn;

    @JsonProperty("book_name")
    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "author fullName cannot be blank")
    private String authorFullName;

    @NotNull(message = "stock cannot be null")
    private Integer stock;

    @NotNull(message = "price cannot be null")
    private BigDecimal price;

    @Version
    private Long version;




    @JsonCreator
    public static Book createBookWithId(BookId id, Isbn isbn,@JsonProperty("book_name") String name, String authorFullName, int stock, BigDecimal price) {
        Assert.notNull(isbn, "isbn cannot be null");
       Book book = new Book();
       book.id = id;
       book.isbn = isbn;
       book.name = name;
       book.authorFullName = authorFullName;
       book.stock = stock;
       book.price = price;

       return book;
    }

    public static Book createBook(Isbn isbn, @JsonProperty("book_name") String name, String authorFullName, int stock, BigDecimal price){
        Book book = new Book();
        book.isbn = isbn;
        book.name = name;
        book.authorFullName = authorFullName;
        book.stock = stock;
        book.price = price;

        return book;
    }


    // HIGH-READ, LOW-WRITE


}
