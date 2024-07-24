package com.example.crudapplication.book.Domain;

import com.example.crudapplication.author.Domain.Author;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Optional;


@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Cacheable
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;


    @JsonCreator
    public Book(BookId id, Isbn isbn,@JsonProperty("book_name") String name, String authorFullName, int stock, BigDecimal price, Author author) {
        Assert.notNull(isbn, "isbn cannot be null");
        this.id = id;
        this.name = name;
        this.authorFullName = authorFullName;
        this.stock =stock;
        this.price = price;
        this.author = author;
    }

    public Book(Isbn isbn, @JsonProperty("book_name") String name, String authorFullName, int stock, BigDecimal price, Author author){
        this.isbn = isbn;
        this.name = name;
        this.authorFullName = authorFullName;
        this.stock = stock;
        this.price = price;
        this.author = author;
    }




}
