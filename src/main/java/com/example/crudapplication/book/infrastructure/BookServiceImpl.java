package com.example.crudapplication.book.infrastructure;

import com.example.crudapplication.Exception.ErrorCode;
import com.example.crudapplication.Exception.GeneralException;
import com.example.crudapplication.book.Application.BookService;
import com.example.crudapplication.book.Domain.BookDto;
import com.example.crudapplication.book.Domain.Book;
import com.example.crudapplication.book.Domain.BookId;
import com.example.crudapplication.book.Domain.BookRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
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
    public Optional<BookDto> findBookById(BookId id) {
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

}
