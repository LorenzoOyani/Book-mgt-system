package com.example.crudapplication.order.Domain.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    Set<OrderItemsRequest> orderItemsDetails;
}
