package com.company.usersecurity.repository;

import com.company.usersecurity.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD ,attributePaths = {"authorities"})
    Optional<User> findByUsername(String username);
}
