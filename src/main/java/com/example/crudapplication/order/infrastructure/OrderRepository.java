package com.example.crudapplication.order.infrastructure;

import com.example.crudapplication.order.Domain.model.Order;
import com.example.crudapplication.users.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Page<Order> findAllOrdersByUserId(UserId  userId,Pageable pageable);

    Page<Order> findAllByOrderIsBetweenDate(LocalDateTime startDate, LocalDateTime endDate, Pageable paginationRequest);

    <T> T   findById(String id, Class<T> tClass);

}
