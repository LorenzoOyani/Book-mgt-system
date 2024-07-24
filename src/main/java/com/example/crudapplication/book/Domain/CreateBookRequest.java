package com.example.crudapplication.book.Domain;

import com.example.crudapplication.author.Domain.AuthorId;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;


@Data
public class CreateBookRequest {
    private BookId id;
  private  Isbn isbn;
  private  String bookName;
  private  String fullName;
  private  Integer stock;
   private  BigDecimal price;
   private AuthorId authorId;
};
