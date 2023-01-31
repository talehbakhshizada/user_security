package com.company.usersecurity.controller;

import com.company.usersecurity.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@Slf4j
@RequiredArgsConstructor
public class GreetingController {

    private final CourseService service;


    //for aop
    @GetMapping("/hello/{id}")
    public ResponseEntity<String> greeting(@PathVariable String id) {
        log.info("real metod invocation used");
        return ResponseEntity.ok("Hello");
    }


    //for all
    @GetMapping("/hello")
    public ResponseEntity<String> greeting() {
        return ResponseEntity.ok("Hello");
    }

    //    for authenticated user and managers
    @GetMapping("/user")
    public ResponseEntity<String> user(Authentication authentication) {
        //service.getCourses();
        return ResponseEntity.ok("welcome to the site " + authentication.getName());
    }


    //for authenticated managers
    @PostMapping("/user")
    public ResponseEntity<String> save() {
        return ResponseEntity.ok("save user");
    }

    //    for authenticated  managers
    @GetMapping("/manager")
    public ResponseEntity<String> manager(Authentication authentication) {
        return ResponseEntity.ok("welcome to the site with role manager MR " + authentication.getName());
    }


}
