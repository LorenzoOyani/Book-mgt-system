package com.example.crudapplication.book;

import com.example.crudapplication.book.Domain.Book;
import com.example.crudapplication.book.Domain.BookDto;
import com.example.crudapplication.book.Domain.BookRepository;
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
        String authorName = "Eggy mule";
        Book book = new Book();
        book.setId(1);
        book.setIsbn("1234");
        book.setName("Some Book");
        book.setAuthorFullName(authorName);
        book.setStock(10);
        book.setPrice(new BigDecimal("29.99"));

        BookDto bookDto = new BookDto(1, "1234", "Some Book", authorName, 10, new BigDecimal("29.99"));

        when(bookRepository.findBookByAuthorFullName(authorName)).thenReturn(book);
        when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);

        BookDto result = bookService.searchBookByAuthorsFullName(authorName);

        assertNotNull(result);
        assertEquals(bookDto, result);
    }
}
