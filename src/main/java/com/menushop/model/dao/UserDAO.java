package com.menushop.model.dao;

import com.menushop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {

    List<User> findAll();

    User findById(long id);

    Optional<User> findByUsername(String name);

    Optional<User> findByEmail(String email);

    void deleteById(long id);
}
