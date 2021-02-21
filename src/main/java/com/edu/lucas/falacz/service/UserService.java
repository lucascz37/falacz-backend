package com.edu.lucas.falacz.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.edu.lucas.falacz.model.User;
import com.edu.lucas.falacz.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User save(User user) throws DataIntegrityViolationException{

        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new DataIntegrityViolationException("Email Already Registered");
        }

        byte[] hashedPassword = BCrypt.withDefaults().hash(6, user.getPassword().getBytes(StandardCharsets.UTF_8));

        user.setPassword(new String(hashedPassword, StandardCharsets.UTF_8));

        return userRepository.save(user);
    }
}
