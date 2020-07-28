package com.liviubiur.savingaccountservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Configuration
@Profile("test")
public class MockClockConfig {

  private final static LocalDate WEEKEND = LocalDate.of(2020, 07, 05);

  @Bean
  public Clock clock() {
    return Clock.fixed(WEEKEND.atTime(9, 5).toInstant(ZoneOffset.UTC), ZoneId.of("CET"));
  }

}
