package com.liviubiur.savingaccountservice.security;

import com.liviubiur.savingaccountservice.user.persistence.entity.User;
import com.liviubiur.savingaccountservice.user.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = Optional.ofNullable(userRepository.getUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found.")));

    return new UserDetailsDTO(user.get());
  }

}
