package com.example.crudapplication.users;

import com.example.crudapplication.order.Domain.model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {


    @EmbeddedId
    private UserId  userId;

    private String fullName;

    private String username;

    private String email;

    private String password;

//    @OneToMany(mappedBy = "Order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Order> order;
}
