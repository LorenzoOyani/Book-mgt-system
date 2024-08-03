package com.example.crudapplication.order.Domain.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginationFindRequest{

   private  DateIntervalRequest dateIntervalRequest;

   private  PaginationRequest paginationRequest;
}
