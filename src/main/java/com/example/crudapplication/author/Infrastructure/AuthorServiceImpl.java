package com.example.crudapplication.author.Infrastructure;

import com.example.crudapplication.author.Application.AuthorService;
import com.example.crudapplication.author.Domain.Author;
import com.example.crudapplication.author.Domain.AuthorId;
import com.example.crudapplication.author.Domain.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<AuthorDto> createAuthor(CreateAuthorPayload payload) {
        log.info("Author name request {} ", payload);
        Author author = new Author();
        author.setName(payload.getName());
        Optional<Author> author1 = Optional.of(authorRepository.save(author));
        return author1.map(author2 ->  modelMapper.map(author2, AuthorDto.class));
    }

    @Override
    public Optional<AuthorDto> findAuthorById(AuthorId id) {
        Optional<Author> authorId = authorRepository.findById(id);
        return authorId.map(authorIds -> modelMapper.map(authorIds, AuthorDto.class));

    }
}
