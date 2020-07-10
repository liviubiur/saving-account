package com.ingtech.challenge.account.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ingtech.challenge.user.persistence.entity.User;

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
