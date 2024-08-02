package com.example.crudapplication.order.infrastructure;

public class OrderServiceImpl{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }



}
