package com.ingtech.challenge.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ingtech.challenge.user.persistence.entity.User;
import com.ingtech.challenge.user.persistence.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public EntityModel<User> newUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole("ROLE_USER");
    user.setEnabled(true);

    return new EntityModel<>(userRepository.save(user));
  }

}
