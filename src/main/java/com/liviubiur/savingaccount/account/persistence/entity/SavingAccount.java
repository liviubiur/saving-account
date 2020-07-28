package com.liviubiur.savingaccount.account.persistence.entity;

import com.liviubiur.savingaccount.user.persistence.entity.User;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Data
@Table(name = "saving_accounts", schema = "public")
public class SavingAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private BigDecimal balance;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "users_username", nullable = false)
  private User user;

  public SavingAccount(BigDecimal balance) {
    this.balance = balance;
  }

}
