package com.example.crudapplication.order.infrastructure;

import com.example.crudapplication.order.Application.OrderService;
import com.example.crudapplication.order.Domain.dto.OrderDTO;
import com.example.crudapplication.order.Domain.orderMapper.OrderMapper;
import com.example.crudapplication.order.Domain.payload.DateIntervalRequest;
import com.example.crudapplication.order.Domain.payload.PaginationFindRequest;
import com.example.crudapplication.order.Domain.payload.PaginationRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public Optional<OrderDTO> findOrderById(Long id) {
        return Optional.empty();
    }

    /**
     * @method returns a list of paginated orders within a specific date.
     */
    @Override
    public Page<OrderDTO> findAllOrdersBySpecificDateIntervals(PaginationFindRequest paginationFindRequest) {
        DateIntervalRequest dateIntervalRequest = paginationFindRequest.getDateIntervalRequest();
        PaginationRequest paginationRequest = paginationFindRequest.getPaginationRequest();
        return orderRepository.findAllByOrderIsBetweenDate(
                dateIntervalRequest.getStartDate(),
                dateIntervalRequest.getEndDate(),
                paginationRequest.toPageable())
                .map(OrderMapper::toOrderDTO);
    }
}
