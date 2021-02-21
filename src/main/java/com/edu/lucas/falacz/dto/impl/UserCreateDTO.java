package com.edu.lucas.falacz.dto.impl;

import com.edu.lucas.falacz.dto.CreateDTO;
import com.edu.lucas.falacz.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

//DTO used for requests to create new user
@Getter @Setter
public class UserCreateDTO implements CreateDTO<User> {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private String base64Image;

    @Override
    public User createObject() {
        return new User(username, email, password, base64Image);
    }

}
