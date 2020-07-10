package com.ingtech.challenge.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ingtech.challenge.user.persistence.UserDetailsDTO;
import com.ingtech.challenge.user.persistence.entity.User;
import com.ingtech.challenge.user.persistence.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.getUserByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User not found.");
    }

    return new UserDetailsDTO(user);
  }

}
