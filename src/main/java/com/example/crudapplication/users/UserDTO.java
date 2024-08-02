package com.example.crudapplication.users;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UserId  userId;

    private String fullName;

    private String username;

    private String email;

    private String password;
}
