package com.liviubiur.savingaccountservice.account.rest;

import com.liviubiur.savingaccountservice.account.persistence.entity.SavingAccount;
import com.liviubiur.savingaccountservice.account.service.SavingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/saving-accounts", produces = APPLICATION_JSON_VALUE)
public class SavingAccountRestController implements SavingAccountRestApi {

  private final SavingAccountService savingAccountService;

  @Autowired
  public SavingAccountRestController(
      SavingAccountService savingAccountService) {
    this.savingAccountService = savingAccountService;
  }

  @Override
  @PostMapping
  public ResponseEntity<?> newSavingAccount(@RequestBody SavingAccount savingAccount) {
    EntityModel<SavingAccount> newSavingAccount = savingAccountService
        .newSavingAccount(savingAccount);

    return new ResponseEntity<>(newSavingAccount, HttpStatus.CREATED);
  }

}
