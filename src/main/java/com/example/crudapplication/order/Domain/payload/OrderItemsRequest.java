package com.example.crudapplication.order.Domain.payload;

import com.example.crudapplication.book.Domain.model.BookId;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderItemsRequest {

    @NotNull
    private BookId bookId;

    @Min(1)
    private int amount;
}
