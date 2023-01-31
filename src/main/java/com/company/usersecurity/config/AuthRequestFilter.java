package com.company.usersecurity.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class AuthRequestFilter extends OncePerRequestFilter {

    private final List<AuthService> authServiceList;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Optional<Authentication> authOptional= Optional.empty();

        for(AuthService authService: authServiceList){
            authOptional=authOptional.or(()->authService.getAuthentication(request));
        }
        authOptional.ifPresent(auth-> SecurityContextHolder.getContext().setAuthentication(auth));
        filterChain.doFilter(request, response);//pass to next filter

    }
}
