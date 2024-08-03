package com.example.crudapplication.book.Domain.payload;

import com.example.crudapplication.book.Domain.model.BookId;
import com.example.crudapplication.book.Domain.model.Isbn;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


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

    @CreationTimestamp
    private LocalDateTime localDateTime;
};
