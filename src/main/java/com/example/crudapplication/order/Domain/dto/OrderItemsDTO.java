package com.example.crudapplication.order.Domain.dto;

import com.example.crudapplication.book.Domain.model.BookId;
import com.example.crudapplication.book.Domain.model.Isbn;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsDTO {
    private Long  id;

    @JsonProperty("books")
    private OrderItemBook book;


    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemBook{
        @JsonProperty("id")
        BookId id;
        @NotNull
        Isbn isbn;
        String name;
        @Size(min = 5, max = 250)
        String authorFullName;
        @Min(1)
        Integer stock;
        BigDecimal price;
        Long version;

    }

}
