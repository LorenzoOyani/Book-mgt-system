package com.example.crudapplication.book.infrastructure;

import com.example.crudapplication.book.Domain.model.Book;
import com.example.crudapplication.book.Domain.model.BookId;
import com.example.crudapplication.book.Domain.model.Isbn;
import jakarta.persistence.LockModeType;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, BookId> {
    Book findBookByAuthorFullName(String authorName);

    @Query(value = "select b from book as b where b.author_full_name LIKE :prefix")
    List<Book> findBookByAuthorFullNameStartingWith(@Param("prefix") String prefix);

    Optional<Book> findByIsbn(Isbn isbn);


    @Lock(LockModeType.OPTIMISTIC)
    Optional<Book> findBookById(BookId bookId);
};