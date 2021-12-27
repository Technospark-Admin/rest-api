package com.technospark.api.restapi.service.impl;

import com.technospark.api.restapi.entity.User;
import com.technospark.api.restapi.model.LoginRequestDTO;
import com.technospark.api.restapi.repository.UserRepository;
import com.technospark.api.restapi.service.UserService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.security.Key;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private String secret = "secretKey";

    @Autowired
    private UserRepository userRepository;


    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public String login(LoginRequestDTO request) {
         Optional<User> record = userRepository.findByUserId(request.getUserId());
         if(record.isPresent()){
             if(record.get().getPassword().equals(request.getPassword())){
                 return generateToken(record.get().getName());
             }else {
                 log.error("Incorrect Password");
                 return null;
             }

         }else {
             log.error("User not found with id " + request.getUserId());
             return null;
         }
    }

    @Override
    public String validateAndGenrateNewToken(String token) throws ServletException {
        String subject = validateToken(token);
        if(null != subject){
            return generateToken(subject);
        } else {
            throw new ServletException("Invalid token");
        }
    }

    private String generateToken (String subject){

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 60000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }

    private String validateToken(String token){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return claims.getBody().getSubject();
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return null;
    }
}
