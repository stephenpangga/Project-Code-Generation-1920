package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.User;
import io.swagger.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@WebMvcTest(AccountsApiController.class)
class AccountsApiControllerTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    private AccountService accountService;

    private Account account;

    @BeforeEach
    public void setUp(){
        account = new Account(100.0,
                new User("email@gmail.com",
                        "password",
                        "email",
                        "bank",
                        User.AccessLevelEnum.CUSTOMER),
                Account.AccountTypeEnum.CURRENT);
    }
    @Test
    public void CallingAllAccountShouldReturnOK() throws Exception {

        List<Account> allAccounts = Arrays.asList(account);
        given(accountService.GetAllAccounts()).willReturn(allAccounts);
        mvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));


    }
    @Test
    public void should_Return404_When_AccountsNotFound() throws Exception {
        given(accountService.GetAllAccounts()).willReturn(null);
        mvc.perform(get("/account")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
    @Test
    public void CreatingaNewAccShouldReturnTheCreatedAcc() throws Exception {
        this.mvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON)
                            .content("{ }"))
                .andExpect(status().isCreated());

    }
    @Test
    public void UpdatingAccShouldReturnTheUpdatedAcc() throws Exception {
        given(accountService.UpdateAccount(account,"NL01INHO0000000001"))
                .willReturn(account);

        this.mvc.perform(put("api/accounts/NL01INHO0000000001"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType("application/json"));

    }

}