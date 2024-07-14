package com.example.crudapplication.book;

import com.example.crudapplication.book.bookEntiity.Book;
import com.example.crudapplication.book.bookEntiity.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ApplicationModuleTest
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class BookTest {

    @Mock
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private BookService bookService;

    @Test
    void testCreateBook() throws Exception {
        BookDto bookDto = new BookDto(1, "1298", "Deception Point", "Dan Brown", 1, new BigDecimal("453.4"));

        when(bookService.createBook(any(Book.class))).thenReturn(Optional.of(bookDto));

        Book book = new Book();
        book.setIsbn("1298");
        book.setName("Deception Point");
        book.setAuthorFullName("Dan Brown");
        book.setStock(1);
        book.setPrice(new BigDecimal("453.4"));
        String mappers = objectMapper.writeValueAsString(book);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mappers))
                .andExpect(status().isCreated());
    }
}
