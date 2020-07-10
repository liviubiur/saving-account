package com.ingtech.challenge.account.rest;

import org.springframework.http.ResponseEntity;

import com.ingtech.challenge.account.persistence.entity.SavingAccount;

public interface SavingAccountRestApi {

  /**
   * Create a new saving account
   *
   * @param savingAccount the saving account entity to create
   * @return the response entity for the new saving account entity
   */
  ResponseEntity<?> newSavingAccount(SavingAccount savingAccount);
}
