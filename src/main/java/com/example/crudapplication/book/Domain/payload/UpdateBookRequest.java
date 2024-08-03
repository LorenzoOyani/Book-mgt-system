package com.example.crudapplication.book.Domain.payload;

import com.example.crudapplication.book.Domain.model.BookId;
import com.example.crudapplication.book.Domain.model.Isbn;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@Setter
public class UpdateBookRequest {

    @NotNull
    private BookId id;

    @NotNull
    @Size(min = 10, max = 13)
    private Isbn isbn;

    @NotBlank
    private String bookName;

    @NotBlank
    private String fullName;

    @Min(value = 0, message = "stock cannot be null")
    private Integer stock;

    @NotNull
    private BigDecimal price;

    @Version
    private Long version;

}
