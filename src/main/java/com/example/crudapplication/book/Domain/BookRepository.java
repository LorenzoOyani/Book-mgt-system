package com.example.crudapplication.book.Domain;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookByAuthorFullName(String authorName);

    @Query(value = "select b from book as b where b.author_full_name LIKE :prefix")
    List<Book> findBookByAuthorFullNameStartingWith(@Param("prefix") String prefix);
};