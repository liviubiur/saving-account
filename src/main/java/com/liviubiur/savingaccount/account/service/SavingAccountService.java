package com.liviubiur.savingaccount.account.service;

import com.liviubiur.savingaccount.account.exception.ScheduleException;
import com.liviubiur.savingaccount.account.exception.UserAccountException;
import com.liviubiur.savingaccount.account.exception.WeekDaysException;
import com.liviubiur.savingaccount.account.persistence.entity.SavingAccount;
import com.liviubiur.savingaccount.account.persistence.repository.SavingAccountRepository;
import com.liviubiur.savingaccount.user.persistence.entity.User;
import com.liviubiur.savingaccount.user.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Service
public class SavingAccountService {

  @Autowired
  private final SavingAccountRepository savingAccountRepository;
  @Autowired
  private final UserRepository userRepository;
  @Autowired
  private Clock clock;

  @Autowired
  public SavingAccountService(
      SavingAccountRepository savingAccountRepository, UserRepository userRepository, Clock clock) {
    this.savingAccountRepository = savingAccountRepository;
    this.userRepository = userRepository;
    this.clock = clock;
  }

  public EntityModel<SavingAccount> newSavingAccount(SavingAccount savingAccount) {
    LocalDateTime localDateTime = LocalDateTime.now(clock);

    if (localDateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) || localDateTime.getDayOfWeek()
        .equals(DayOfWeek.SUNDAY)) {
      throw new WeekDaysException();
    } else if (localDateTime.getHour() < 9 || localDateTime.getHour() > 17) {
      throw new ScheduleException();
    }

    Authentication principal = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.getUserByUsername(principal.getName());

    savingAccount.setUser(user);

    try {
      return new EntityModel<>(savingAccountRepository.save(savingAccount));
    } catch (DataIntegrityViolationException e) {
      throw new UserAccountException();
    }
  }

}
