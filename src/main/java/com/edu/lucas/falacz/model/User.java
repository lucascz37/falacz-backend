package com.edu.lucas.falacz.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users",
uniqueConstraints= @UniqueConstraint(columnNames = {"email"}))
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    @NotNull
    @NotBlank
    String username;

    @NotNull
    @NotBlank
    @Email
    String email;

    @NotNull
    @NotBlank
    String password;

    @Lob
    String base64Image;

    public User(@NotBlank String username, @NotBlank @Email String email, @NotBlank String password, String base64Image) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.base64Image = base64Image;
    }
}
