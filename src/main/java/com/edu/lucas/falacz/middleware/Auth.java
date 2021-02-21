package com.edu.lucas.falacz.middleware;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.edu.lucas.falacz.configuration.Properties;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Ensure authentication
public class Auth extends GenericFilterBean {
    private final JWTVerifier verifier;

    public Auth(Properties props) {
        this.verifier = JWT.require(Algorithm.HMAC256(props.getSecret())).build();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        verifyAuth(req, resp, chain);

    }

    //Return the decodedJWT if the token is valid
    public void verifyAuth(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException{
        String[] arr = req.getHeader("Authorization").split(" ");
        try {
            if (arr[0].equals("Bearer")){
                req.setAttribute("id", verifier.verify(arr[1]).getSubject());
                chain.doFilter(req, resp);
            }
        }catch (JWTVerificationException | ArrayIndexOutOfBoundsException e){
            resp.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

    }
}
