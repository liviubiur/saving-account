package com.liviubiur.savingaccountservice.user.rest;

import com.liviubiur.savingaccountservice.user.persistence.entity.User;
import com.liviubiur.savingaccountservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
public class UserRestController implements UserRestApi {

  private final UserService userService;

  @Autowired
  public UserRestController(UserService userService) {
    this.userService = userService;
  }

  @Override
  @PostMapping()
  public ResponseEntity<?> newUser(@RequestBody User user) {
    EntityModel<User> newUser = userService.newUser(user);

    return new ResponseEntity<>(newUser, HttpStatus.CREATED);
  }

}
