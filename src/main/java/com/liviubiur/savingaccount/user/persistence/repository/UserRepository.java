package com.liviubiur.savingaccount.user.persistence.repository;

import com.liviubiur.savingaccount.user.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

  User getUserByUsername(@Param("username") String username);

}
