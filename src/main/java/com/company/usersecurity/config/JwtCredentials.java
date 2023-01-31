package com.company.usersecurity.config;

import com.company.usersecurity.domain.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class JwtCredentials {

    Long iat;
    String username;
    Integer userId;
    Long  exp;
    List<Role> role;
}
