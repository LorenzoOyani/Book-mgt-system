package com.example.crudapplication.order.Application;

import com.example.crudapplication.order.Domain.dto.OrderDTO;

import java.util.Optional;

public interface OrderService {

    Optional<OrderDTO> findOrderById(Long  id);
}
