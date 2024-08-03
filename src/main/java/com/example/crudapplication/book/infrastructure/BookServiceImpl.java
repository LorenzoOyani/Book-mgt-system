package com.example.crudapplication.book.infrastructure;

import com.example.crudapplication.Exception.ErrorCode;
import com.example.crudapplication.Exception.GeneralException;
import com.example.crudapplication.book.Application.BookService;
import com.example.crudapplication.book.Domain.EntityException.NoAvailableStockException;
import com.example.crudapplication.book.Domain.Exception.BookNotFoundException;
import com.example.crudapplication.book.Domain.bookMapper.BookMapper;
import com.example.crudapplication.book.Domain.dto.BookDto;
import com.example.crudapplication.book.Domain.model.*;
import com.example.crudapplication.book.Domain.payload.CreateBookRequest;
import com.example.crudapplication.book.Domain.payload.UpdateBookRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;


    private final ModelMapper modelMapper;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository,  ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<BookDto> createBook(CreateBookRequest request, LocalDateTime dateTime) {
        Book createdBook = BookFactory.createBook(request, dateTime );
        Book savedBook = bookRepository.save(createdBook);
        BookDto bookDto = BookMapper.mapToDto(savedBook);
        return Optional.of(bookDto);
    }


    @Transactional
    @Override
    public Optional<BookDto> findBookById(BookId id) {
        Optional<Book> book = bookRepository.findBookById(id);
        return book.map(value -> modelMapper.map(value, BookDto.class));
    }

    @Transactional
    @Override
    public Page<BookDto> findBook(Pageable pageable) {
        Page<Book> pageableBook = bookRepository.findAll(pageable);
        return pageableBook.map(paged -> modelMapper.map(paged, BookDto.class));
    }

    @Transactional
    @Override
    public BookDto searchBookByAuthorsFullName(String authorName) {
        Book searchbook = bookRepository.findBookByAuthorFullName(authorName);
        if (searchbook == null || !searchbook.getAuthorFullName().equals(authorName)) {
            throw new GeneralException(ErrorCode.AUTHOR_NOT_FOUND);
        }
        return BookMapper.mapToDto(searchbook);
    }

    @Override
    public Page<BookDto> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(book ->modelMapper.map (book, BookDto.class));
    }

    public List<BookDto> findBookByAuthorFullNameStartingWith(String prefix){
       List<Book> book =  bookRepository.findBookByAuthorFullNameStartingWith(prefix);
       if(book == null || book.isEmpty()  || !book.getFirst().getAuthorFullName().startsWith(prefix, 1)){
           throw new GeneralException(ErrorCode.PREFIX_NOT_FOUND);
       }
        if(!book.isEmpty()){
            log.info(" The list of books found are {}", book.getFirst());
        }
       return book.stream().map(n -> modelMapper.map(n, BookDto.class))
               .collect(Collectors.toList());
    }


    @Override
    public Optional<BookDto> findByIsbn(String isbn) {
        Isbn isbnValue = IsbnFactory.CreateIsbn(isbn);
        Optional<Book> book = bookRepository.findByIsbn(isbnValue);
        return book.map(books -> modelMapper.map(book, BookDto.class));
    }

    @Override
    public boolean isStockAvailable(BookDto bookDto, int amount) {

        if(bookDto.getStock() < amount){
            throw new NoAvailableStockException(amount);
        }
        return true;
    }

    @Override
    public BookDto updateStockById(BookId id, UpdateBookRequest updateBookRequest) {

        Book book = bookRepository.findBookById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setStock(updateBookRequest.getStock());

        BookMapper.mapBookToUpdate(book, updateBookRequest);

        return BookMapper.mapToDto(book);

    }

}
