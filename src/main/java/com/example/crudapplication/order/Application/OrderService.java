package com.example.crudapplication.order.Application;

import com.example.crudapplication.order.Domain.dto.OrderDTO;
import com.example.crudapplication.order.Domain.payload.PaginationFindRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface OrderService {

    Optional<OrderDTO> findOrderById(Long  id);

    Page<OrderDTO> findAllOrdersBySpecificDateIntervals(PaginationFindRequest paginationFindRequest);
}
