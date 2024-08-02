package com.example.crudapplication.order.Application;

import com.example.crudapplication.order.Domain.dto.OrderDTO;
import com.example.crudapplication.order.Domain.payload.CreateOrderRequest;

public interface OrderUseCase {

    OrderDTO createOrder(CreateOrderRequest orderRequest);
}
