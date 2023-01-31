package com.company.usersecurity;

import com.company.usersecurity.config.JwtService;
import com.company.usersecurity.domain.Authority;
import com.company.usersecurity.domain.Role;
import com.company.usersecurity.domain.User;
import com.company.usersecurity.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class UserSecurityApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public static void main(String[] args) {
        SpringApplication.run(UserSecurityApplication.class, args);
    }

   @Override
    public void run(String... args) throws Exception {
        Authority authority = new Authority();
        authority.setRole(Role.MANAGER);
        User user = User.builder()
                .username("Taleh")
                .password(passwordEncoder.encode("12345"))
                .isAccountNonExpired(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .isAccountNonLocked(true)
                .authorities(Set.of(authority))
                .build();

        userRepository.save(user);
        log.info("user: "+user);

        String s = jwtService.issueToken(user);
        log.info("Token generated {} ",s);

        Claims claims = jwtService.parseToken(s);
        log.info("Token is parsed {}",claims);

    }

}
