package com.example.crudapplication.order.infrastructure;

import com.example.crudapplication.book.Application.BookService;
import com.example.crudapplication.book.Domain.bookMapper.BookMapper;
import com.example.crudapplication.book.Domain.dto.BookDto;
import com.example.crudapplication.book.Domain.model.Book;
import com.example.crudapplication.book.Domain.payload.UpdateBookRequest;
import com.example.crudapplication.order.Domain.orderMapper.OrderItemMapper;
import com.example.crudapplication.order.Application.OrderItemService;
import com.example.crudapplication.order.Domain.dto.OrderItemsDTO;
import com.example.crudapplication.order.Domain.model.OrderItems;
import com.example.crudapplication.order.Domain.orderMapper.OrderMapper;
import com.example.crudapplication.order.Domain.payload.OrderItemsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final BookService bookService;

    public OrderItemServiceImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public OrderItemsDTO createOrderItem(OrderItemsRequest orderItemsRequest) {
        Optional<BookDto> bookDtoOptional = bookService.findBookById(orderItemsRequest.getBookId());

        if (bookDtoOptional.isEmpty()) {
            log.warn("book with id of {} not found", orderItemsRequest.getBookId());
        }
        BookDto bookDto = bookDtoOptional.get();

        bookService.isStockAvailable(bookDto, orderItemsRequest.getAmount());
        Book book = BookMapper.toBook(bookDto);

        OrderItems orderItems = OrderItems.builder()
                .book(book)
                .amount(orderItemsRequest.getAmount())
                .build();

        UpdateBookRequest updateBookStockRequest;
        updateBookStockRequest = UpdateBookRequest.builder()
                .stock(bookDto.getStock() - orderItems.getAmount())
                .build();

        bookService.updateStockById(bookDto.getId(), updateBookStockRequest);

        return OrderItemMapper.toOrderItemsDTO(orderItems);


    }
}