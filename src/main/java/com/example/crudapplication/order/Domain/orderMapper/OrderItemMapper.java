package com.example.crudapplication.order.Domain.orderMapper;

import com.example.crudapplication.book.Domain.model.Book;
import com.example.crudapplication.book.Domain.model.BookId;
import com.example.crudapplication.book.Domain.model.Isbn;
import com.example.crudapplication.order.Domain.dto.OrderItemsDTO;
import com.example.crudapplication.order.Domain.model.OrderItems;

public class OrderItemMapper {

    public static OrderItemsDTO toOrderItemsDTO(OrderItems orderItems){
        return OrderItemsDTO.builder()
                .id(orderItems.getId())
                .book(toBook(orderItems.getBook()))
                .build();
    }

    public static OrderItemsDTO.OrderItemBook  toBook(Book source){
        return OrderItemsDTO.OrderItemBook.builder()
                .id(new BookId())
                .isbn(new Isbn())
                .name(source.getName())
                .authorFullName(source.getAuthorFullName())
                .stock(source.getStock())
                .price(source.getPrice())
                .version(source.getVersion())
                .build();
    }
}
