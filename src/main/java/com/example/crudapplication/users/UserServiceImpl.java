package com.example.crudapplication.users;

import com.example.crudapplication.order.Application.OrderService;
import com.example.crudapplication.order.infrastructure.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    public UserServiceImpl(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }


    @Override
    public Optional<UserDTO> findUserById(UserId id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            log.warn("user with id of {} not found", id );
        }
        User user = userOptional.get();

        return Optional.of(UserMapper.toUserDTO(user));

    }

    @Override
    public UserDTO findAllUsersOrdersById(UserId id, Pageable pageable) {
        return null;
    }
}
