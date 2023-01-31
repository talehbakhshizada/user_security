package com.company.usersecurity.config;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenAuthService implements AuthService {
    private final JwtService jwtService;
    public static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";


    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION);
        String token = "";
        if (authorization != null && authorization.startsWith(BEARER)) {
            token = authorization.substring(BEARER.length());
            boolean result = jwtService.validateJwtToken(token,request);
            if(result){
                Claims claims = jwtService.parseToken(token);
                Authentication authenticationBearer = jwtService.getAuthenticationBearer(claims);
                return Optional.of(authenticationBearer);
            }
            return Optional.empty();
        } else {
            return Optional.empty();
        }
    }
}
