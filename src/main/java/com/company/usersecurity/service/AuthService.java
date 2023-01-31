package com.company.usersecurity.service;

import com.company.usersecurity.config.JwtService;
import com.company.usersecurity.domain.Authority;
import com.company.usersecurity.domain.Role;
import com.company.usersecurity.domain.User;
import com.company.usersecurity.exception.InvalidPasswordException;
import com.company.usersecurity.model.request.LoginRequest;
import com.company.usersecurity.model.response.ApiResponse;
import com.company.usersecurity.model.response.JwtResponse;
import com.company.usersecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public ApiResponse register(LoginRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new com.company.usersecurity.exception.ExistsByUsernameException();
        }
        Authority authority = new Authority();
        authority.setRole(Role.MANAGER);
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .isAccountNonExpired(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .isAccountNonLocked(true)
                .authorities(Set.of(authority))
                .build();
        userRepository.save(user);
        return new ApiResponse("Register process is done successfully");
    }

    public JwtResponse signIn(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException(request.getUsername()));
        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(matches){
            return JwtResponse.builder()
                    .jwtResponse(jwtService.issueToken(user))
                    .build();
        }else{
            throw new InvalidPasswordException();
        }

    }

}
