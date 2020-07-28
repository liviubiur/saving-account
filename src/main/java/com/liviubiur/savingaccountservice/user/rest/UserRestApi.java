package com.liviubiur.savingaccountservice.user.rest;

import com.liviubiur.savingaccountservice.user.persistence.entity.User;
import org.springframework.http.ResponseEntity;

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
