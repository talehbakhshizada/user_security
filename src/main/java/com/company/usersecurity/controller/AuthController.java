package com.company.usersecurity.controller;

import com.company.usersecurity.model.request.LoginRequest;
import com.company.usersecurity.model.response.ApiResponse;
import com.company.usersecurity.model.response.JwtResponse;
import com.company.usersecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ApiResponse register(@RequestBody LoginRequest request) {
        return authService.register(request);
    }

    @PostMapping("/sign-in")
    public JwtResponse singIn(@RequestBody LoginRequest loginRequest) {
        return  authService.signIn(loginRequest);
    }


}
