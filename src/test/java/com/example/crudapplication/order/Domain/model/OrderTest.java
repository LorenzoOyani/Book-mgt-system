package com.example.crudapplication.order.Domain.model;

import com.example.crudapplication.book.Application.BookService;
import com.example.crudapplication.book.Domain.dto.BookDto;
import com.example.crudapplication.book.Domain.model.BookId;
import com.example.crudapplication.order.Domain.dto.OrderDTO;
import com.example.crudapplication.order.Domain.orderMapper.OrderMapper;
import com.example.crudapplication.order.Domain.payload.OrderItemsRequest;
import com.example.crudapplication.order.infrastructure.OrderItemServiceImpl;
import com.example.crudapplication.order.infrastructure.OrderRepository;
import com.example.crudapplication.order.infrastructure.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OrderTest {

    @Mock
    private BookService bookService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderItemService;

    private OrderDTO orderDTO;

    private Order order;




    @BeforeEach
    void initialize() {
        orderDTO = new OrderDTO();
        order = new Order();

    }

    @Test
    public void testFindOrderById_OrderFound() {
        Long orderId = 1L;

        //arrange
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        //Act
        Optional<OrderDTO> result;
        try(MockedStatic<OrderMapper>  mapperMockedStatic  = mockStatic(OrderMapper.class)){
            mapperMockedStatic.when(()-> OrderMapper.toOrderDTO(order)).thenReturn(orderDTO);

             result = orderItemService.findOrderById(orderId);

             //assert
             assertTrue(result.isPresent());
             assertEquals(orderDTO, result.get());

             //verify

            verify(orderRepository, times(1)).findById(orderId);
            mapperMockedStatic.verify(()->OrderMapper.toOrderDTO(order),    times(1));
        }


    }


}