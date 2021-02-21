package com.edu.lucas.falacz.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.edu.lucas.falacz.configuration.Properties;
import com.edu.lucas.falacz.model.User;
import com.edu.lucas.falacz.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Service
public class SessionService {

    private final UserRepository userRepository;
    private final Properties props;
    private final long duration = TimeUnit.DAYS.toMillis(1);

    public String generateToken(String email, String password){
        User user = findByEmail(email);

        if(user != null && checkPassword(user, password)){
            return generateToken(user);
        }

        return null;
    }

    private User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    private boolean checkPassword(User user, String passwordToCheck){
        BCrypt.Result result = BCrypt.verifyer().verify(passwordToCheck.toCharArray(), user.getPassword());
        return result.verified;
    }

    private String generateToken(User user){
        return JWT.create()
                .withSubject(String.valueOf(user.getId()))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + duration))
                .sign(Algorithm.HMAC256(props.getSecret()));
    }
}
