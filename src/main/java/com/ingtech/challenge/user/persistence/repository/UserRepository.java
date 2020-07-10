package com.ingtech.challenge.user.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ingtech.challenge.user.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

  User getUserByUsername(@Param("username") String username);

}
