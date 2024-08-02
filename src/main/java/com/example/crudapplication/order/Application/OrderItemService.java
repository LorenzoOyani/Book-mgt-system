package com.example.crudapplication.order.Application;

import com.example.crudapplication.order.Domain.dto.OrderItemsDTO;
import com.example.crudapplication.order.Domain.payload.OrderItemsRequest;

public interface OrderItemService {

    OrderItemsDTO createOrderItem(OrderItemsRequest orderItemsRequest);
}
