package com.liviubiur.savingaccountservice.account;

import com.liviubiur.savingaccountservice.SavingAccountServiceApplication;
import com.liviubiur.savingaccountservice.account.persistence.entity.SavingAccount;
import com.liviubiur.savingaccountservice.configuration.MockClockWorkingConfig;
import com.liviubiur.savingaccountservice.user.SecurityConfigurationTest;
import com.liviubiur.savingaccountservice.user.persistence.entity.User;
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

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT,
    classes = {SavingAccountServiceApplication.class, MockClockWorkingConfig.class})
@ActiveProfiles("test")
public class SavingAccountTestWorkingTimeTest {

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
        .content(SecurityConfigurationTest.asJsonString(user))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON));
  }

  @Test()
  public void createAccount_thenAssertionSucceeds() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders
        .post(SAVING_ACCOUNT_PATH)
        .with(httpBasic(user.getUsername(), user.getPassword()))
        .content(SecurityConfigurationTest.asJsonString(savingAccount))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.balance").value(20));
  }

  @Test()
  public void createTwoAccountsForTheSameUser_thenFailed() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders
        .post(SAVING_ACCOUNT_PATH)
        .with(httpBasic(user.getUsername(), user.getPassword()))
        .content(SecurityConfigurationTest.asJsonString(savingAccount))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

    String error = Objects.requireNonNull(this.mockMvc.perform(MockMvcRequestBuilders
        .post(SAVING_ACCOUNT_PATH)
        .with(httpBasic(user.getUsername(), user.getPassword()))
        .content(SecurityConfigurationTest.asJsonString(savingAccount))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isConflict())
        .andReturn().getResolvedException()).getMessage();

    assertTrue(error.contains("The user can have only one savings account."));
  }

}
