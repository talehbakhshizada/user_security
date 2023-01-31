package com.company.usersecurity.service;

import com.company.usersecurity.config.JwtCredentials;
import com.company.usersecurity.config.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseService {

    private final SecurityService securityService;
    public void getCourses() {

        JwtCredentials jwtCredentials = securityService.getCurrentJwtCredentials();
        log.info("username is " + jwtCredentials.getUsername());
        log.info("user id is " + jwtCredentials.getUserId());
    }
}

