package com.example.crudapplication.order.Domain.model;


import com.example.crudapplication.book.Domain.model.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItems {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    @Min(value = 1, message = "amount of ordered Items  should  be  >=1")
    private int amount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id",nullable = false,referencedColumnName = "id")
    private Order order;
}
