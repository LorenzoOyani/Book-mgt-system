package com.example.crudapplication.order.Domain.model;

import com.example.crudapplication.book.Domain.dto.BookDto;
import com.example.crudapplication.book.Domain.model.BookId;
import com.example.crudapplication.order.Domain.payload.OrderItemsRequest;
import com.example.crudapplication.order.infrastructure.OrderItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderTest {

    @InjectMocks
    private OrderItemServiceImpl  orderItemService;


    @BeforeEach
    void test(){
        BookId bookId=new BookId();
        OrderItemsRequest  orderItemsRequest=new OrderItemsRequest();
        BookDto bookDto=new BookDto();
        bookDto.setId(bookId);
        bookDto.setStock(100);
        orderItemsRequest.setBookId(bookId);
        orderItemsRequest.setAmount(5);
    }



}