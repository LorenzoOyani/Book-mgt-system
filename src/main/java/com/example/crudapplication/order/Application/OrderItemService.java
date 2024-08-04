package com.example.crudapplication.order.Application;

import com.example.crudapplication.order.Domain.dto.OrderItemsDTO;
import com.example.crudapplication.order.Domain.payload.OrderItemsRequest;
import org.springframework.stereotype.Component;

@Component
public interface OrderItemService {

    OrderItemsDTO createOrderItem(OrderItemsRequest orderItemsRequest);
}
