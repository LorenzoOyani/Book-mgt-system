package com.example.crudapplication.order.infrastructure;

import com.example.crudapplication.Exception.ErrorCode;
import com.example.crudapplication.Exception.GeneralException;
import com.example.crudapplication.order.Application.OrderService;
import com.example.crudapplication.order.Domain.dto.OrderDTO;
import com.example.crudapplication.order.Domain.model.Order;
import com.example.crudapplication.order.Domain.orderMapper.OrderMapper;
import com.example.crudapplication.order.Domain.payload.DateIntervalRequest;
import com.example.crudapplication.order.Domain.payload.PaginationFindRequest;
import com.example.crudapplication.order.Domain.payload.PaginationRequest;
import com.example.crudapplication.users.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public Optional<OrderDTO> findOrderById(Long id) {
        Optional<Order> order = Optional.ofNullable(orderRepository.findById(id).orElseThrow(() -> new GeneralException(ErrorCode.RECORD_NOT_FOUND)));
        return order.map(OrderMapper::toOrderDTO);
    }

    @Override
    public Page<OrderDTO> findAllOrdersByUserId(UserId id, Pageable pageable) {
        Page<Order> orders =orderRepository.findAllOrdersByUserId(id, pageable);
        return new PageImpl<>(
                    orders.stream()
                            .map(OrderMapper::toOrderDTO)
                            .collect(Collectors.toList()),
                            pageable,
                    orders.getTotalPages()
        );

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
