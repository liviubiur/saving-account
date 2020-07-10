package com.ingtech.challenge.user.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ingtech.challenge.account.persistence.entity.SavingAccount;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Data
@Table(name = "users", schema = "public")
public class User {

  @Id
  private String username;
  private String password;
  private String email;
  private String role;
  private boolean enabled;

  @Transient
  @JsonIgnore
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
  private SavingAccount savingAccount;

  public User(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

}
