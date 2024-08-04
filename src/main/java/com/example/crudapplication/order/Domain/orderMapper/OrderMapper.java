package com.example.crudapplication.order.Domain.orderMapper;

import com.example.crudapplication.order.Domain.dto.OrderDTO;
import com.example.crudapplication.order.Domain.model.Order;
import com.example.crudapplication.users.UserMapper;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class OrderMapper {

public  OrderDTO toOrderDTO(Order source){
    return OrderDTO.builder()
            .id(source.getOrderId())
            .user(UserMapper.toUserDTO(source.getUser()))
            .createdAt(LocalDateTime.now()).build();
}

}
