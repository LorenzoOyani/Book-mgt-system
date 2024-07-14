package com.example.crudapplication.book;


import com.example.crudapplication.Exception.ErrorCode;
import com.example.crudapplication.Exception.GeneralException;
import com.example.crudapplication.book.bookEntiity.Book;
import com.example.crudapplication.book.bookEntiity.BookRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
@Transactional
class BookService {


    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Optional<BookDto> createBook(Book book) {
        try {

                Book createdBook = bookRepository.save(book);
//                var bookDto = new BookDto(createdBook.getId(), createdBook.getName(), createdBook.getIsbn(),
//                        createdBook.getAuthorFullName(), createdBook.getStock(), createdBook.getPrice()); // can be replaced with model mappers

            BookDto bookDto = modelMapper.map(createdBook, BookDto.class);

                return Optional.of(bookDto); //
            } catch (Exception e) {
            throw new GeneralException(ErrorCode.BOOK_NOT_FOUND);
        }
    }
}




