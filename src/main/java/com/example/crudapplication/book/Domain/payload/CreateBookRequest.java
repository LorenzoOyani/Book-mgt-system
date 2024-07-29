package com.example.crudapplication.book.Domain.payload;

import com.example.crudapplication.book.Domain.BookId;
import com.example.crudapplication.book.Domain.Isbn;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class CreateBookRequest {

    @NotNull
    private BookId id;

    @NotNull
    private Isbn isbn;

    @NotBlank
    private String bookName;

    @NotBlank
    private String fullName;

    @NotNull
    private Integer stock;

    @NotNull
    private BigDecimal price;

    @Version
    private Long version;
};
