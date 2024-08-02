package com.example.crudapplication.book.Domain.controller;

import com.example.crudapplication.Exception.ErrorCode;
import com.example.crudapplication.Exception.GeneralException;
import com.example.crudapplication.book.Application.BookService;
import com.example.crudapplication.book.Domain.dto.BookDto;
import com.example.crudapplication.book.Domain.model.BookId;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
class BookController {

    private final BookService bookService;
    private final ModelMapper modelMapper;

    BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

//    @PostMapping("/createBook")
//    public ResponseEntity<Optional<BookDto>> createBook(@Validated @RequestBody Book book) {
//        Optional<BookDto> books = bookService.createBook(
//                        book.getId(),
//                        book.getIsbn(),
//                        book.getName(),
//                        book.getAuthorFullName(),
//                        book.getStock(),
//                        book.getPrice()
//
//        );
//        return books.map(bookId -> modelMapper.map(new ResponseEntity<>(BookDto.class, HttpStatus.CREATED)))
//                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
//
//    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findBookById(@PathVariable BookId id) {
        Optional<BookDto> books = bookService.findBookById(id);
        return books.map(newBookDto -> new ResponseEntity<>(newBookDto, HttpStatus.OK))
                .orElseThrow(() -> new GeneralException(ErrorCode.BOOK_NOT_FOUND));
    }

    @GetMapping("/pages")
    public ResponseEntity<Page<BookDto>> findBook(Pageable pageable) {
        Page<BookDto> listsOfPage = bookService.findBook(pageable);
        return new ResponseEntity<>(listsOfPage, HttpStatus.OK);
    }

    @GetMapping("/author")
    public ResponseEntity<BookDto> searchAuthorName(@RequestParam String authorName) {
        BookDto bookDto = bookService.searchBookByAuthorsFullName(authorName);
        if (bookDto != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Page<BookDto>> getPageableBook(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort[0]).ascending());
        Page<BookDto> bookDtoPage = bookService.getBooks(pageable);
        if (bookDtoPage != null) {
            return new ResponseEntity<>(bookDtoPage, HttpStatus.OK);

        } else {
            throw new GeneralException(ErrorCode.RECORD_NOT_FOUND);
        }

    }

    @GetMapping("/books/by-author-firstname")
    public List<BookDto> getBooksByAuthorFirstNameStartingWith(@RequestParam String prefix) {
        return bookService.findBookByAuthorFullNameStartingWith(prefix);

    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDto> findByIsbn(@PathVariable String isbn) {
        Optional<BookDto> findIsbn = bookService.findByIsbn(isbn);
        return findIsbn.map(books -> new ResponseEntity<>(books, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
