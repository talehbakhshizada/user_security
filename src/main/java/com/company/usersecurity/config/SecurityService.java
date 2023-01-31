package com.company.usersecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    private final ObjectMapper mapper=new ObjectMapper();

    public JwtCredentials getCurrentJwtCredentials(){
        var securityContext= SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        return mapper.convertValue(authentication.getPrincipal(), JwtCredentials.class);
    }
}
