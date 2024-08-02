package com.example.crudapplication.order.Domain.dto;


import com.example.crudapplication.users.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long  id;
    private UserDTO user;
    private LocalDateTime createdAt;
    private List<OrderItemsDTO> orderItemsDTOList;
}
