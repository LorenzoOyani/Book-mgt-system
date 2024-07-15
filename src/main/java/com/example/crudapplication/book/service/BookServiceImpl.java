package com.example.crudapplication.book.service;

import com.example.crudapplication.Exception.ErrorCode;
import com.example.crudapplication.Exception.GeneralException;
import com.example.crudapplication.book.BookDto;
import com.example.crudapplication.book.bookEntiity.Book;
import com.example.crudapplication.book.bookEntiity.BookRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Optional<BookDto> createBook(Book book) {
        try {
            Book createdBook = bookRepository.save(book);
            BookDto bookDto = modelMapper.map(createdBook, BookDto.class);
            return Optional.of(bookDto);
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.BOOK_NOT_FOUND);
        }
    }

    @Transactional
    @Override
    public Optional<BookDto> findBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
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
        return modelMapper.map(searchbook, BookDto.class);
    }

    @Override
    public Page<BookDto> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(book ->modelMapper.map (book, BookDto.class));
    }
}
