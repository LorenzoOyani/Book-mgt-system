package com.example.crudapplication.order.infrastructure;

import com.example.crudapplication.book.Application.BookService;
import com.example.crudapplication.book.Domain.bookMapper.BookMapper;
import com.example.crudapplication.book.Domain.dto.BookDto;
import com.example.crudapplication.book.Domain.model.Book;
import com.example.crudapplication.book.Domain.payload.BookUpdateStockRequest;
import com.example.crudapplication.order.Domain.orderMapper.OrderItemMapper;
import com.example.crudapplication.order.Application.OrderItemService;
import com.example.crudapplication.order.Domain.dto.OrderItemsDTO;
import com.example.crudapplication.order.Domain.model.OrderItems;
import com.example.crudapplication.order.Domain.payload.OrderItemsRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class OrderItemServiceImpl implements OrderItemService {

    private final BookService bookService;

    public OrderItemServiceImpl(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public OrderItemsDTO createOrderItem(OrderItemsRequest orderItemsRequest) {

       Optional<BookDto> bookDto = bookService.findBookById(orderItemsRequest.getBookId());
       Book book;
        OrderItems orderItems = null;
       if(bookDto.isPresent()){
           log.info("book found in dataBase by id of {} ", bookDto.get().getId());
           book = BookMapper.toBook(bookDto.get());
           bookService.isStockAvailable(bookDto.get(), orderItemsRequest.getAmount());
           orderItems = OrderItems.builder()
                   .book(book).build();
       }
       BookUpdateStockRequest updateStockRequest;
        if(bookDto.isPresent()){
            updateStockRequest = BookUpdateStockRequest.builder()
                    .stock(bookDto.get().getStock() - orderItemsRequest.getAmount())
                    .build();

            bookService.updateStockById(bookDto.get().getId(), updateStockRequest);

        }
        assert false;
        return OrderItemMapper.toOrderItemsDTO(orderItems);
    }
}
