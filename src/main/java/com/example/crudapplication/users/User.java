package com.example.crudapplication.users;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {


    @EmbeddedId
    private UserId  userId;

    private String fullName;

    private String username;

    private String email;

    private String password;
}
