package com.example.crudapplication.author.Domain;

import com.example.crudapplication.book.Domain.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Author {
    @EmbeddedId
    @NotNull(message = "Author id cannot be null")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private AuthorId id;

    @OneToMany(mappedBy = "author_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> book;

    @NotNull(message = "name cannot be null")
    @Column(name = "Author_name", unique = false, nullable = false)
    private String name;

    public Author(String name) {
        this.id = new AuthorId();
        this.name = name;
    }

    public Author() {

    }

    public Author(AuthorId authorId, String name) {
        this.id = authorId;
        this.name = name;
    }
}
