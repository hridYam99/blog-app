package com.example.blog.app.apis.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    int id;

    @NotNull
    String name;
    @Email
    String email;
    @NotNull
    String about;
    @NotNull
    String password;
}
