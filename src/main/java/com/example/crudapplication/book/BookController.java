package com.example.crudapplication.book;

import com.example.crudapplication.Exception.ErrorCode;
import com.example.crudapplication.Exception.GeneralException;
import com.example.crudapplication.book.bookEntiity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/books")
class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody Book book) {
        Optional<BookDto> createdBook = bookService.createBook(book);

        return createdBook
                .map(bookDto -> new ResponseEntity<>(bookDto, HttpStatus.CREATED))
                .orElseThrow(() -> new GeneralException(ErrorCode.BOOK_NOT_FOUND));
    }
}
