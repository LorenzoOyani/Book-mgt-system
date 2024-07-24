package com.example.crudapplication.book;

import com.example.crudapplication.author.Domain.Author;
import com.example.crudapplication.author.Domain.AuthorId;
import com.example.crudapplication.book.Domain.*;
import com.example.crudapplication.book.infrastructure.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        // No need to open mocks here since it's done by MockitoExtension
    }

    @Test
    void testSearchBookBy_AuthorFullName_Found() {
        AuthorId id = new AuthorId(1L);
        String authorName = "Eggy mule";
        Book book = new Book();
        BookId bookId = new BookId(1);
        Isbn isbn = new Isbn("1298");
        Author author = new Author(id, "mike");
        author.setName("big dawg");

        book.setId(bookId);
        book.setIsbn(isbn);
        book.setName("Some Book");
        book.setAuthorFullName(authorName);
        book.setStock(10);
        book.setPrice(new BigDecimal("29.99"));
        book.setAuthor(author);

        BookDto bookDto = new BookDto(new BookId(4), new Isbn("1298"), "Some Book", authorName, 10, new BigDecimal("29.99"), id);

        when(bookRepository.findBookByAuthorFullName(authorName)).thenReturn(book);
        when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);

        BookDto result = bookService.searchBookByAuthorsFullName(authorName);

        assertNotNull(result);
        assertEquals(bookDto, result);
    }
}
