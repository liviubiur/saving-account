package com.liviubiur.savingaccountservice.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class UserAccountException extends RuntimeException {

  public UserAccountException() {
    super("The user can have only one savings account.");
  }
}
