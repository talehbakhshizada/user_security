package com.company.usersecurity.config;

import com.company.usersecurity.domain.Authority;
import com.company.usersecurity.domain.User;
import com.company.usersecurity.exception.UsernameNotFoundException;
import com.company.usersecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasicAuthService implements AuthService{

    private final UserRepository userRepository;
    public static final String AUTHORIZATION = "Authorization";
    private static final String BASIC = "Basic ";
    private final PasswordEncoder encoder;


    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest request) {
        var authorization = request.getHeader(AUTHORIZATION);
        if (authorization != null && authorization.startsWith(BASIC)) {
            String basic = authorization.substring(BASIC.length());
            byte[] decode = Base64.getDecoder().decode(basic);
            String[] split = new String(decode).split(":");
            if (split.length != 2) {
                throw new RuntimeException();
            }
            User user = userRepository.findByUsername(split[0]) // 0->username
                    .orElseThrow(() -> new UsernameNotFoundException());
            boolean matches = encoder.matches(split[1], user.getPassword());
            if (matches) {
                Authentication authenticationBasic = getAuthenticationBasic(user);
                return Optional.of(authenticationBasic);
            }
        }
        return Optional.empty();
    }

    public Authentication getAuthenticationBasic(User user) {
        List<?> roles = user.getAuthorities().stream().map(Authority::getRole).collect(Collectors.toList());
        List<GrantedAuthority> authorityList = roles
                .stream()
                .map(a -> new SimpleGrantedAuthority(a.toString()))
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(user, "", authorityList);
    }

}
