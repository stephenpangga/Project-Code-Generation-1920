package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountServiceTest {

    @Autowired
    private MockMvc mvc;
   @Autowired
   AccountService service;
    @MockBean
    private AccountRepository accountRepository;
    private Account account;

    @BeforeEach
    public void Setup(){
        account = new Account(2, Account.AccountTypeEnum.SAVINGS);
    }
    @Test
    public void CallingAllAccountsShouldReturnOK() throws Exception {
        given( accountRepository.findAll())
                .willReturn(Arrays.asList(account));
        this.mvc.perform(get("/accounts"))
                .andExpect(status().isOk());
    }

}