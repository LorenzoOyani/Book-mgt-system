package com.example.crudapplication.book.Domain;

import com.example.crudapplication.config.BookIdConverter;
import com.example.crudapplication.config.IsbnConverter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.commons.beanutils.converters.StringConverter;
import org.springframework.util.Assert;

import java.math.BigDecimal;


@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@EqualsAndHashCode(callSuper = true)
@Entity
@Cacheable
@Table(name = "book")
public class Book {


    @EmbeddedId
    private BookId id;

    @Column(name = "Isbn", unique = true)
    @NotBlank(message = "Isbn cannot be blank")

    @AttributeOverride(name = "value", column = @Column(name = "Isbn"))
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


    @JsonCreator
    public Book(BookId id, Isbn isbn,@JsonProperty("book_name") String name, String authorFullName, int stock, BigDecimal price) {
        this.id = id;
        Assert.notNull(isbn, "isbn cannot be null");
        this.name = name;
        this.authorFullName = authorFullName;
        this.stock =stock;
        this.price = price;
    }




}
