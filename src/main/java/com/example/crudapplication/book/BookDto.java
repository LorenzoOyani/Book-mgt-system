package com.example.crudapplication.book;

import java.math.BigDecimal;
import java.util.UUID;

public record BookDto(int id, String isbn, String name, String authorFullName, Integer stock, BigDecimal price) {};
