package com.example.crudapplication.order.infrastructure;

import com.example.crudapplication.order.Domain.model.Order;
import com.example.crudapplication.users.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Page<Order> findAllByUserId(UserId  userId,Pageable pageable);
}
