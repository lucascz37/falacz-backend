package com.edu.lucas.falacz.controller;

import com.edu.lucas.falacz.dto.impl.UserAuthDTO;
import com.edu.lucas.falacz.service.SessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/session")
@AllArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<ObjectNode> createToken(@Valid @RequestBody UserAuthDTO user){
        String generatedToken = sessionService.generateToken(user.getEmail(), user.getPassword());
        if(generatedToken != null){
            ObjectNode obj = objectMapper.createObjectNode();
            obj.put("token", generatedToken);
            return new ResponseEntity<>(obj, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
