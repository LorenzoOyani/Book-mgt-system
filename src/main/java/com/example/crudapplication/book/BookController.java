package com.example.crudapplication.book;

import com.example.crudapplication.Exception.ErrorCode;
import com.example.crudapplication.Exception.GeneralException;
import com.example.crudapplication.book.bookEntiity.Book;
import com.example.crudapplication.book.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/books")
class BookController {

    private final BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping
    public ResponseEntity<BookDto> createBook(@Validated  @RequestBody Book book) {
        Optional<BookDto> createdBook = bookService.createBook(book);

        return createdBook
                .map(bookDto -> new ResponseEntity<>(bookDto, HttpStatus.CREATED))
                .orElseThrow(() -> new GeneralException(ErrorCode.BOOK_NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findBookById(@PathVariable Long id){
        Optional<BookDto> books = bookService.findBookById(id);
        return books.map(newBookDto -> new ResponseEntity<>(newBookDto, HttpStatus.OK))
                .orElseThrow(()-> new GeneralException(ErrorCode.BOOK_NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Page<BookDto>> findBook(Pageable pageable){
        Page<BookDto> listsOfPage = bookService.findBook(pageable);
        return new ResponseEntity<>(listsOfPage, HttpStatus.OK);
    }

    @GetMapping("/AUTHOR")
    public ResponseEntity<BookDto> searchAuthorName(@RequestParam String authorName){
        BookDto bookDto = bookService.searchBookByAuthorsFullName(authorName);
        if(bookDto != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Page<BookDto>> getPageableBook(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "id,asc") String[] sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort[0]).ascending());
        Page<BookDto> bookDtoPage = bookService.getBooks(pageable);
        if(bookDtoPage !=  null){
            return new ResponseEntity<>(bookDtoPage, HttpStatus.OK);

        }else{
            throw new GeneralException(ErrorCode.RECORD_NOT_FOUND);
        }

    }

}
