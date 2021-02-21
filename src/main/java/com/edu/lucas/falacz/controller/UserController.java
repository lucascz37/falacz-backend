package com.edu.lucas.falacz.controller;

import com.edu.lucas.falacz.dto.impl.UserCreateDTO;
import com.edu.lucas.falacz.dto.impl.UserResponseDTO;
import com.edu.lucas.falacz.model.User;
import com.edu.lucas.falacz.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> postUser(@Valid @RequestBody UserCreateDTO body){
        try {
            User newUser = userService.save(body.createObject());
            return new ResponseEntity<>(
                    new UserResponseDTO(newUser.getEmail(), newUser.getUsername()),
                    HttpStatus.ACCEPTED);
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }

    }
}
