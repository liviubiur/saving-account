package com.liviubiur.savingaccount.account.persistence.repository;

import com.liviubiur.savingaccount.account.persistence.entity.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingAccountRepository extends JpaRepository<SavingAccount, Long> {

}
