package com.liviubiur.savingaccountservice.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liviubiur.savingaccountservice.SavingAccountServiceApplication;
import com.liviubiur.savingaccountservice.user.persistence.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SavingAccountServiceApplication.class)
@ActiveProfiles("test")
public class SecurityConfigurationTest {

  @Autowired
  private WebApplicationContext wac;
  private MockMvc mockMvc;

  private User user;

  @Before
  public void setUp() {
    user = new User("user", "password", "user@test.com");
    this.mockMvc = webAppContextSetup(this.wac).build();
  }

  @Test
  public void noUserAuthRequested_thenSuccess() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders
        .post("/users")
        .content(asJsonString(user))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").exists());
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
