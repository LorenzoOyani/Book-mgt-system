package com.example.crudapplication.users;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Optional<UserDTO> findUserById(UserId id);

    UserDTO findAllUsersOrdersById(UserId id, Pageable pageable);
}
