package com.example.crudapplication.author.Infrastructure;

import com.example.crudapplication.author.Domain.AuthorId;
import com.example.crudapplication.book.Domain.Book;
import jakarta.persistence.EmbeddedId;

import java.util.List;

public class AuthorDto {

    private AuthorId id;
    private String name;
    private List<Book> book;





}
