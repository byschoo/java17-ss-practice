package com.byschoo.jwtpractice1.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.byschoo.jwtpractice1.Entities.User;


@Repository
public interface IUserRepository extends JpaRepository<User, Long>{

    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
