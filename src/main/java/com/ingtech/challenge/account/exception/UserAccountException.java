package com.ingtech.challenge.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class UserAccountException extends RuntimeException {

  public UserAccountException() {
    super(String.format("The user can have only one savings account."));
  }
}
