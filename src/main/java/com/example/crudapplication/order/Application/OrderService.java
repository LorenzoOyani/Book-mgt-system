package com.example.crudapplication.order.Application;

import com.example.crudapplication.order.Domain.dto.OrderDTO;
import com.example.crudapplication.order.Domain.payload.PaginationFindRequest;
import com.example.crudapplication.users.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface OrderService {

    Optional<OrderDTO> findOrderById(Long  id);

    Page<OrderDTO> findAllOrdersByUserId(UserId id, Pageable pageable);

    Page<OrderDTO> findAllOrdersBySpecificDateIntervals(PaginationFindRequest paginationFindRequest);
}
