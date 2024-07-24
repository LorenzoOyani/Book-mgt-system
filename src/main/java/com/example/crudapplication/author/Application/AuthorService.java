package com.example.crudapplication.author.Application;

import com.example.crudapplication.author.Domain.Author;
import com.example.crudapplication.author.Domain.AuthorId;
import com.example.crudapplication.author.Infrastructure.AuthorDto;
import com.example.crudapplication.author.Infrastructure.CreateAuthorPayload;

import java.util.Optional;

public interface AuthorService {

    Optional<AuthorDto> createAuthor(CreateAuthorPayload payload);

    Optional<AuthorDto> findAuthorById(AuthorId id);
}
