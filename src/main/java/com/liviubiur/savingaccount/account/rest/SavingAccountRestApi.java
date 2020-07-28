package com.liviubiur.savingaccount.account.rest;

import com.liviubiur.savingaccount.account.persistence.entity.SavingAccount;
import org.springframework.http.ResponseEntity;

public interface SavingAccountRestApi {

  /**
   * Create a new saving account
   *
   * @param savingAccount the saving account entity to create
   * @return the response entity for the new saving account entity
   */
  ResponseEntity<?> newSavingAccount(SavingAccount savingAccount);
}
