package com.example.crudapplication.book.infrastructure;

import com.example.crudapplication.Exception.ErrorCode;
import com.example.crudapplication.Exception.GeneralException;
import com.example.crudapplication.author.Domain.Author;
import com.example.crudapplication.author.Domain.AuthorId;
import com.example.crudapplication.author.Domain.AuthorRepository;
import com.example.crudapplication.book.Application.BookService;
import com.example.crudapplication.book.Domain.*;
import com.example.crudapplication.book.Domain.EntityException.EntityErrorCodes;
import com.example.crudapplication.book.Domain.EntityException.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }



    @Transactional
    @Override
    public Optional<BookDto> createBook(BookId id, Isbn isbn, String bookName, String fullName, Integer stock, BigDecimal price, AuthorId authorid) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorid);

        if(optionalAuthor.isEmpty()){
            throw new EntityNotFoundException(EntityErrorCodes.ENTITY_NOT_FOUND);
        }
        Author author = optionalAuthor.get();
        try {
            Book newBook = BookFactory.createBook(id,isbn, bookName, fullName, stock, price, author);
            bookRepository.save(newBook);

            return Optional.ofNullable(modelMapper.map(newBook, BookDto.class));
        }catch (Exception ignored){
            throw new EntityNotFoundException(EntityErrorCodes.SAVE_BOOK_ERROR);
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


    @Override
    public Optional<BookDto> findByIsbn(String isbn) {
        Isbn isbnValue = IsbnFactory.CreateIsbn(isbn);
        Optional<Book> book = bookRepository.findByIsbn(isbnValue);
        return book.map(books -> modelMapper.map(book, BookDto.class));
    }

}
