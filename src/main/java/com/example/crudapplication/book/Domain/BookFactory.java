package com.example.crudapplication.book.Domain;

import com.example.crudapplication.author.Domain.Author;

import java.math.BigDecimal;
import java.util.Optional;

public class BookFactory {
    public static Book createBook(BookId id, Isbn isbn, String bookName, String fullName, Integer stock, BigDecimal price,Author author){
     Book book = new Book();
     book.setId(id);
     book.setIsbn(isbn);
     book.setName(bookName);
     book.setAuthorFullName(fullName);
     book.setStock(stock);
     book.setPrice(price);
     book.setAuthor(author);
     return book;

    }
}
