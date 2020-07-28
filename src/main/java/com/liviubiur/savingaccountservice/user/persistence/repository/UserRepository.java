package com.liviubiur.savingaccountservice.user.persistence.repository;

import com.liviubiur.savingaccountservice.user.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> getUserByUsername(@Param("username") String username);

}
