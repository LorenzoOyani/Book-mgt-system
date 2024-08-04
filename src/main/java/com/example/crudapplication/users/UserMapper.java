package com.example.crudapplication.users;

import lombok.Builder;
import lombok.experimental.UtilityClass;

@UtilityClass
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
