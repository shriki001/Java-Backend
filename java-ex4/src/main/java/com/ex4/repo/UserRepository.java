package com.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * interface that extends {@link JpaRepository}
 * and handle the User connection with the database
 */
public interface UserRepository extends JpaRepository<User, Long> {
    // return the user by given name
    List<User> findByName(String name);

    // return the last user from the database
    User findFirstByOrderByIdDesc();
}
