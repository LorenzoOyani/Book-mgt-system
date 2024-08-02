package com.example.crudapplication.book.Domain.payload;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookUpdateStockRequest {

    @Min(value =  0, message = "stock must be >=1")
    private Integer stock;
}
