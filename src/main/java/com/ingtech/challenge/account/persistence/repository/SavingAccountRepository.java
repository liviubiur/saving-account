package com.ingtech.challenge.account.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingtech.challenge.account.persistence.entity.SavingAccount;

public interface SavingAccountRepository extends JpaRepository<SavingAccount, Long> {

}
