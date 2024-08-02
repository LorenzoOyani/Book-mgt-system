package com.example.crudapplication.users;

public class UserMapper {

    public  static UserDTO toUserDTO(User   user){
        return  UserDTO.builder()
                .userId(new UserId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
