package org.learningspringwithduc.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.learningspringwithduc.userservice.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
