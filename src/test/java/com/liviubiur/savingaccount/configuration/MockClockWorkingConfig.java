package com.liviubiur.savingaccount.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Configuration
@Profile("test")
public class MockClockWorkingConfig {

  private final static LocalDate WORKING_DAYS_CORRECT_TIME = LocalDate.of(2020, 07, 06);

  @Bean
  public Clock clock() {
    return Clock
        .fixed(WORKING_DAYS_CORRECT_TIME.atTime(11, 5).toInstant(ZoneOffset.UTC), ZoneId.of("CET"));
  }

}
