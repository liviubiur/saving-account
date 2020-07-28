package com.liviubiur.savingaccountservice.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ScheduleException extends RuntimeException {

  public ScheduleException() {
    super(String.format("Please return tomorrow between 9 AM and 18 PM to open your account."));
  }

}
