package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountsApiControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    private Account account;
    @BeforeEach
    public void Setup(){
        account = new Account(2, Account.AccountTypeEnum.CURRENT);
    }
    @Test
    public void CallingAllAccountShouldReturnOK() throws Exception {
        given(accountService.GetAllAccounts())
        .willReturn(Arrays.asList(account));

        this.mvc.perform(get("/accounts"))
        .andExpect(status().isOk())
        .andExpect((ResultMatcher) jsonPath("$",hasSize(1)));

    }
    @Test
    public void CreatingaNewAccShouldReturnTheCreatedAcc() throws Exception {
        given(accountService.CreateAccount(account))
                .willReturn(account);

        this.mvc.perform(post("/accounts"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$",hasSize(1)));

    }
    @Test
    public void UpdatingAccShouldReturnTheUpdatedAcc() throws Exception {
        given(accountService.UpdateAccount(account,"NL01INHO0000000001"))
                .willReturn(account);

        this.mvc.perform(put("/accounts/NL01INHO0000000001"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType("application/json"));

    }

}