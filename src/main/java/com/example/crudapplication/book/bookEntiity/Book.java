package com.example.crudapplication.book.bookEntiity;

import com.example.crudapplication.book.BookDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.UUID;




@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "book")
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "Isbn", unique = true)
    @NotBlank(message = "Isbn cannot be blank")
    private String isbn;
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
    public Book(int id, String isbn,@JsonProperty("book_name") String name, String authorFullName, int stock, BigDecimal price) {
        this.id = id;
        this.isbn = isbn;
        this.name = name;
        this.authorFullName = authorFullName;
        this.stock =stock;
        this.price = price;
    }


}
