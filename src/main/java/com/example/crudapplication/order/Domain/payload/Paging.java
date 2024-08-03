package com.example.crudapplication.order.Domain.payload;

import org.springframework.data.domain.Pageable;

public interface Paging {



    Pageable toPageable();
}
