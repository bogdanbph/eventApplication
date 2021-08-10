package com.erykandbogdan.eventapp.repository;

import com.erykandbogdan.eventapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, BigDecimal> {
    Optional<User> findByEmail(String email);
    Optional<User> findByLastName(String lastName);
    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);
}
