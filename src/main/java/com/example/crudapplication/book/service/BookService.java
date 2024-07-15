package com.example.crudapplication.book.service;


import com.example.crudapplication.book.BookDto;
import com.example.crudapplication.book.bookEntiity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;



public interface  BookService {

   Optional<BookDto> createBook(Book book);

   Optional<BookDto> findBookById(Long id);

   Page<BookDto> findBook(Pageable pageable);

   BookDto searchBookByAuthorsFullName(String authorName);

   Page<BookDto> getBooks(Pageable pageable);
}




