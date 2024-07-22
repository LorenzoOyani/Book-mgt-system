package com.example.crudapplication.book.Application;


import com.example.crudapplication.book.Domain.BookDto;
import com.example.crudapplication.book.Domain.Book;
import com.example.crudapplication.book.Domain.BookId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


 public interface  BookService {

   Optional<BookDto> createBook(Book book);

   Optional<BookDto> findBookById(BookId id);

   Page<BookDto> findBook(Pageable pageable);

   BookDto searchBookByAuthorsFullName(String authorName);

   Page<BookDto> getBooks(Pageable pageable);

   List<BookDto> findBookByAuthorFullNameStartingWith(String prefix);
}




