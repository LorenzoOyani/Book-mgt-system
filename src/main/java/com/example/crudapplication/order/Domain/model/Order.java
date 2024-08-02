package com.example.crudapplication.order.Domain.model;

import com.example.crudapplication.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "order_table")
public class Order {

    @Id
    private Long orderId;

    @ManyToOne
    @JoinColumn(name="userId", nullable = false)
    private User user;

    @OneToMany(mappedBy = "Order",  cascade = CascadeType.PERSIST,  fetch = FetchType.EAGER)
    private List<OrderItems> orderItemsList;
}
