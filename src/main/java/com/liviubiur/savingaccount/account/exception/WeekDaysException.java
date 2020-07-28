package com.liviubiur.savingaccount.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class WeekDaysException extends RuntimeException {

  public WeekDaysException() {
    super(String.format("Please return Monday to open your account."));
  }

}
