package com.edu.lucas.falacz.dto.impl;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class PostCreateDTO {

    @NotBlank
    @Size(max = 150)
    private String text;

}
