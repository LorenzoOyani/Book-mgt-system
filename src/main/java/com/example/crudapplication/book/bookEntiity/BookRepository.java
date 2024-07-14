package com.example.crudapplication.book.bookEntiity;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {};