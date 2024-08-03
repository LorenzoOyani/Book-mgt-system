package com.example.crudapplication.book;

import com.example.crudapplication.book.infrastructure.BookRepository;
import com.example.crudapplication.book.Domain.dto.BookDto;
import com.example.crudapplication.book.Domain.model.Book;
import com.example.crudapplication.book.Domain.model.BookId;
import com.example.crudapplication.book.Domain.model.Isbn;
import com.example.crudapplication.book.infrastructure.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ApplicationModuleTest
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class BookTest {

    @Mock
    private BookRepository bookRepository;

    @Mock

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private BookServiceImpl bookService;



    @Test
    void testCreateBook() throws Exception {
        BookDto bookDto = new BookDto(new BookId(1), new Isbn("1298"), "Deception Point", "Dan Brown", 1, new BigDecimal("453.4"));
        Book book = new Book();

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Isbn isbn = new Isbn("345");
        book.setIsbn(isbn);
        book.setName("Deception Point");
        book.setAuthorFullName("Dan Brown");
        book.setStock(1);
        book.setPrice(new BigDecimal("453.4"));
        String mappers = objectMapper.writeValueAsString(book); // mapping to Json strings

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mappers))
                .andExpect(status().isCreated());
    }

    @Test
    public void get_Book_by_id() throws Exception {
        BookDto book1 = new BookDto(new BookId(2), new Isbn("1298"), "Book One", "Author One", 10, new BigDecimal("100.00"));
        BookDto book2 = new BookDto(new BookId(3), new Isbn("1298"), "Book Two", "Author Two", 5, new BigDecimal("150.00"));

        List<BookDto> listClass = Arrays.asList(book1, book2);
        Page<BookDto> paged = new PageImpl<>(listClass);

        when(bookService.findBook(any(Pageable.class))).thenReturn(paged);
        mockMvc.perform(get("/api/books")
                .param("page", "0")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].isbn").value("1234"))
                .andExpect(jsonPath("$.content[1].isbn").value("5678"));



    }
}
