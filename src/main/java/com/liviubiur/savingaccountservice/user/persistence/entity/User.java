package com.liviubiur.savingaccountservice.user.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.liviubiur.savingaccountservice.account.persistence.entity.SavingAccount;
import lombok.*;

import javax.persistence.*;

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
