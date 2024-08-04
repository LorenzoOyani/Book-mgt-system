package com.example.crudapplication.order.Domain.model;

import com.example.crudapplication.book.Application.BookService;
import com.example.crudapplication.book.Domain.dto.BookDto;
import com.example.crudapplication.book.Domain.model.Book;
import com.example.crudapplication.book.Domain.model.BookId;
import com.example.crudapplication.book.Domain.payload.UpdateBookRequest;
import com.example.crudapplication.order.Domain.dto.OrderItemsDTO;
import com.example.crudapplication.order.Domain.orderMapper.OrderItemMapper;
import com.example.crudapplication.order.Domain.payload.OrderItemsRequest;
import com.example.crudapplication.order.infrastructure.OrderItemServiceImpl;
import com.example.crudapplication.order.infrastructure.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderItemTest {


    @Mock
    private BookService bookService;

    @InjectMocks
    private OrderItemServiceImpl orderItemService;

    private OrderItemsRequest itemsRequest;
    BookDto bookDto;


    @BeforeEach
    void test() {
        BookId bookId = new BookId();
        bookDto = new BookDto();
        bookDto.setId(bookId);
        bookDto.setStock(50);

        itemsRequest.setBookId(bookId);
        itemsRequest.setAmount(10);

    }

    @Test
    void createOrderItem_whenItemIsInStockAndAvailable() {
        //arrange
        when(bookService.findBookById(itemsRequest.getBookId())).thenReturn(Optional.of(bookDto));
        doNothing().when(bookService).isStockAvailable(bookDto, itemsRequest.getAmount());
        doNothing().when(bookService).updateStockById(any(BookId.class), any(UpdateBookRequest.class));


        //act
        OrderItems orderItems = OrderItems.builder()
                .book(new Book())
                .amount(itemsRequest.getAmount())
                .build();

        when(OrderItemMapper.toOrderItemsDTO(any(OrderItems.class))).thenReturn(new OrderItemsDTO());

        //result;
        OrderItemsDTO result = orderItemService.createOrderItem(itemsRequest);

        //assert
        assertNotNull("items is present", result);

        //verify
        verify(bookService).findBookById(itemsRequest.getBookId());
        verify(bookService).isStockAvailable(bookDto, itemsRequest.getAmount());
        verify(bookService).updateStockById(any(BookId.class), any(UpdateBookRequest.class));


    }


}
