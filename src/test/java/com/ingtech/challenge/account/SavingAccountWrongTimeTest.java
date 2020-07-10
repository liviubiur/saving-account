package com.ingtech.challenge.account;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ingtech.challenge.ChallengeApplication;
import com.ingtech.challenge.account.persistence.entity.SavingAccount;
import com.ingtech.challenge.configuration.MockClockScheduleConfig;
import com.ingtech.challenge.user.persistence.entity.User;

import static com.ingtech.challenge.user.SecurityConfigurationTest.asJsonString;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT,
    classes = {ChallengeApplication.class, MockClockScheduleConfig.class})
@ActiveProfiles("test")
public class SavingAccountWrongTimeTest {

  private static final String SAVING_ACCOUNT_PATH = "/saving-accounts";

  @Autowired
  private WebApplicationContext wac;
  private MockMvc mockMvc;

  private User user;
  private SavingAccount savingAccount;

  @Before
  public void setUp() throws Exception {
    user = new User("user", "password", "user@test.com");
    savingAccount = new SavingAccount(new BigDecimal(20));

    this.mockMvc = webAppContextSetup(this.wac)
        .apply(springSecurity())
        .build();

    this.mockMvc.perform(MockMvcRequestBuilders
        .post("/users")
        .content(asJsonString(user))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON));
  }

  @Test()
  public void createAccountDuringWeekOutOfWorkingHours_thenExceptionThrown()
      throws Exception {

    String error = this.mockMvc.perform(MockMvcRequestBuilders
        .post(SAVING_ACCOUNT_PATH)
        .with(httpBasic(user.getUsername(), user.getPassword()))
        .content(asJsonString(savingAccount))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isConflict())
        .andReturn().getResolvedException().getMessage();

    assertTrue(
        error.contains("Please return tomorrow between 9 AM and 18 PM to open your account."));
  }

}
