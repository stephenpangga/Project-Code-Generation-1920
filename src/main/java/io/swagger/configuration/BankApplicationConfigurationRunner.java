package io.swagger.configuration;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.TransactionRepository;
import io.swagger.repository.UserRepository;
import io.swagger.service.LoginService;
import io.swagger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.threeten.bp.LocalDateTime;

import java.util.Arrays;
import java.util.List;

@Component
@ConditionalOnProperty(prefix = "Banking_application.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
@Transactional
public class BankApplicationConfigurationRunner implements ApplicationRunner {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        loadUsers();
        LoadAccounts();
        loadTransactions();
    }

    /*** save the datas here ***/
    /* http://localhost:8080/api/h2-console */
    /*^^ the database link**/

    public void loadUsers()
    {
        loginService.setToken(userService.registerUser(
                "inholland@gmail.com",
                "inhollandbank",
                "Inholland",
                "Bank",
                User.AccessLevelEnum.EMPLOYEE
        ), "DEBUG_TOKEN");

        loginService.setToken(userService.registerUser(
                "stephen@gmail.com",
                "password",
                "Stephen",
                "Pangga",
                User.AccessLevelEnum.EMPLOYEE
        ), "DEBUG_TOKEN");

        loginService.setToken(userService.registerUser(
                "frances@gmail.com",
                "frances",
                "Frances",
                "Agasino",
                User.AccessLevelEnum.EMPLOYEE
        ), "DEBUG_TOKEN");

        loginService.setToken(userService.registerUser(
                "thomas@gmail.com",
                "admin",
                "Thomas",
                "de Joode",
                User.AccessLevelEnum.EMPLOYEE
        ), "DEBUG_TOKEN");

        loginService.setToken(userService.registerUser(
                "abc@xyz.com",
                "abc",
                "Customer",
                "Man",
                User.AccessLevelEnum.CUSTOMER
        ), "DEBUG_TOKEN");
    }


    public void loadTransactions()
    {
        List<Transaction> transactionList = Arrays.asList(
                new Transaction(accountRepository.findAll().get(0),
                        accountRepository.findAll().get(1),
                        503.73,
                        Transaction.TransactionTypeEnum.TRANSFER,
                        userRepository.findAll().get(1),
                        LocalDateTime.now()),
                new Transaction(accountRepository.findAll().get(0),
                        accountRepository.findAll().get(1),
                        502.73,
                        Transaction.TransactionTypeEnum.TRANSFER,
                        userRepository.findAll().get(1),
                        LocalDateTime.now()),
                new Transaction(accountRepository.findAll().get(1),
                        accountRepository.findAll().get(0),
                        501.73,
                        Transaction.TransactionTypeEnum.WITHDRAW,
                        userRepository.findAll().get(0),
                        LocalDateTime.now())
        );

        for (Transaction transaction : transactionList) {
            transactionRepository.save(transaction);
        }

    }

    public void LoadAccounts(){

          List<User> users = userRepository.findAll();
          User bankUser = users.get(0);
          User stephen= users.get(0);
          User dogOwner = users.get(0);


          List<Account> accounts = Arrays.asList(
                  new Account(100.0, users.get(0), Account.AccountTypeEnum.CURRENT,5,10000.0,10.0),
                  new Account( 10000.0, users.get(0), Account.AccountTypeEnum.SAVINGS,5,10000.0,10.0),
                  new Account(100.1, users.get(1), Account.AccountTypeEnum.CURRENT,5,10000.0,10.0),
                  new Account(1058.1, users.get(2), Account.AccountTypeEnum.CURRENT,5,10000.0,10.0)
          );
          accounts.forEach(acc->accountRepository.save(acc));
          List<Account>acc = (List<Account>) accountRepository.findAll();
          acc.forEach(System.out::println);

    }
}
