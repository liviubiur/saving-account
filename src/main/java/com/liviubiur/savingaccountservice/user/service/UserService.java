package com.liviubiur.savingaccountservice.user.service;

import com.liviubiur.savingaccountservice.user.persistence.entity.User;
import com.liviubiur.savingaccountservice.user.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

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
