package com.edu.lucas.falacz.dto.impl;

import lombok.Getter;
import lombok.Setter;

//DTO used for responses when new user is created
@Getter @Setter
public class UserResponseDTO {

    private String email;
    private String username;

    public UserResponseDTO(String email, String username) {
        this.email = email;
        this.username = username;
    }

}
