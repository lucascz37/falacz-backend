package com.edu.lucas.falacz.dto.impl;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class UserAuthDTO {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
