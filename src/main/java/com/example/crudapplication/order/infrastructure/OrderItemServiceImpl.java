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
        Optional<BookDto> bookDto = bookService.findBookById(orderItemsRequest.getBookId());
       if(bookDto.isEmpty()){
           log.warn("Book not found with id {}",orderItemsRequest.getBookId());
       }
       BookDto bookDTO =bookDto.get();
       log.info("Book found in dataBase by id of {}",  bookDTO.getId());
       Book book=BookMapper.toBook(bookDTO);
       bookService.isStockAvailable(bookDTO, orderItemsRequest.getAmount());

       OrderItems  orderItems = OrderItems.builder()
                .book(book).
                amount(orderItemsRequest.getAmount())
              .build();
       UpdateBookRequest updateStockRequest;
        updateStockRequest = UpdateBookRequest.builder()
                .stock(bookDto.get().getStock() - orderItemsRequest.getAmount())
                .build();

        bookService.updateStockById(bookDTO.getId(), updateStockRequest);

        return OrderItemMapper.toOrderItemsDTO(orderItems);
    }
}
