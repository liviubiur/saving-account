package com.ingtech.challenge.user.rest;

import org.springframework.http.ResponseEntity;

import com.ingtech.challenge.user.persistence.entity.User;

public interface

UserRestApi {

  /**
   * Create a new user
   *
   * @param user the user entity to create
   * @return the response entity for the new user entity
   */
  ResponseEntity<?> newUser(User user);

}
