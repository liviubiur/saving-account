package com.liviubiur.savingaccountservice.account.persistence.repository;

import com.liviubiur.savingaccountservice.account.persistence.entity.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingAccountRepository extends JpaRepository<SavingAccount, Long> {

}
