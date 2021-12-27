package com.technospark.api.restapi.filter;

import com.technospark.api.restapi.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;


    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Token");
        String newToken = userService.validateAndGenrateNewToken(token);
        response.setHeader("RefreshToken",newToken);
        filterChain.doFilter(request, response);
    }

}