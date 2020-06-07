package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import io.swagger.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.threeten.bp.LocalDateTime;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(TransactionsApiController.class)
class TransactionsApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    //private TransactionsApiController transactionsApiController;
    private TransactionService transactionService;

    //@MockBean
    //private TransactionRepository transactionRepository;

    private Transaction transaction;

    @BeforeEach
    public void setup()
    {
        User employee = new User("inholland@gmail.com",
                "inhollandbank",
                "Bank",
                "bank",
                User.AccessLevelEnum.EMPLOYEE);
        User stephen = new User("stephen@gmail.com",
                "password",
                "Stephen",
                "Pangga",
                User.AccessLevelEnum.EMPLOYEE);
        Account bankAccount = new Account("NL01INHO0000000001",100.0, employee, Account.AccountTypeEnum.CURRENT);
        Account stephenAccount = new Account("NL01INHO0000000019",100.0, stephen, Account.AccountTypeEnum.CURRENT);

        transaction = new Transaction(bankAccount,
                stephenAccount,
                100.0,
                Transaction.TransactionTypeEnum.TRANSFER,
                employee,
                LocalDateTime.now());
    }

    //(expected = NullPointerException.class)
    //@WithMockUser(roles = {"Employee", "Customer"})
    @Test
    public void gettingAllTransactionShouldReturnOK() throws Exception {

        List<Transaction> transactionList = Arrays.asList(transaction);

        given(transactionService.getAllTransactions()).willReturn(transactionList);

        this.mvc.perform(get("/transactions"))
                .andExpect(status().isOk()).andExpect(content().contentType("application/json"));
    }

    @Test
    public void test() throws Exception {
        List<Transaction> transactionList = Arrays.asList(transaction);

        given(transactionService.getAllTransactions()).willReturn(transactionList);

        this.mvc.perform(get("/transactions"))
                .andExpect(status().isOk()).andExpect(content().contentType("application/json"));

    }
    @Test
    public void createTransactionShouldReturn201Created() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        this.mvc
            .perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(transaction)))
                .andExpect(status().isCreated());
    }

}