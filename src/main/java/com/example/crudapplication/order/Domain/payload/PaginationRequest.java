package com.example.crudapplication.order.Domain.payload;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PaginationRequest implements Paging{


    @Min(0)
    private static final int min = 0;


    @Min(1)
    @Max(50)
    private static final int max = 10;

    @Override
    public Pageable toPageable() {
        return PageRequest.of(min, max);
    }
}
