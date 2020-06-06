package io.swagger.configuration;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.TransactionRepository;
import io.swagger.repository.UserRepository;
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

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        loadUsers();
        loadAccounts();
        loadTransactions();
    }

    /*** save the datas here ***/
    /* http://localhost:8080/api/h2-console */

    /*^^ the database link**/

    public void loadUsers()
    {
        List<User> userList = Arrays.asList(
                new User("inholland@gmail.com",
                        "inhollandbank",
                        "Bank",
                        "bank",
                        User.AccessLevelEnum.EMPLOYEE),
                new User("stephen@gmail.com",
                        "password",
                        "Stephen",
                        "pangga",
                        User.AccessLevelEnum.EMPLOYEE
                ),
                new User("frances@gmail.com",
                        "password",
                        "Frances",
                        "Agasino",
                        User.AccessLevelEnum.CUSTOMER
                ),
                new User("sisa@gmail.com",
                        "password",
                        "Sisa",
                        "Mokranova",
                        User.AccessLevelEnum.CUSTOMER
                )
        );
        for(User user: userList)
        {
            userRepository.save(user);
        }
    }


    public void loadTransactions()
    {
        User u = new User("stephen@gmail.com", "password", "Stephen", "pangga", User.AccessLevelEnum.EMPLOYEE);
        u.setId(10002);
        List<Account> a = accountRepository.findAll();

        Account account1 = a.get(0);
        Account account2 = a.get(1);


        List<Transaction> transactionList = Arrays.asList(
                new Transaction(account1,
                        account2,
                        503.73,
                        Transaction.TransactionTypeEnum.TRANSFER,
                        u,
                        LocalDateTime.now()),
                new Transaction(account1,
                        account2,
                        502.73,
                        Transaction.TransactionTypeEnum.TRANSFER,
                        u,
                        LocalDateTime.now()),
                new Transaction(account2,
                        account1,
                        501.73,
                        Transaction.TransactionTypeEnum.WITHDRAW,
                        u,
                        LocalDateTime.now())
        );

        for (Transaction transaction : transactionList) {
            transactionRepository.save(transaction);
        }

        for (Transaction transaction : transactionRepository.findAll()) {
            System.out.println(transaction);
        }
    }
    public void loadAccounts() {
        Account bankAccount = new Account(99999,  1500.0, Account.AccountTypeEnum.CURRENT);
        bankAccount.setIban("NL01INHO0000000001");
        Account account1 = new Account(10003,100.1, Account.AccountTypeEnum.CURRENT);
        account1.setIban("NL23INHO2298608059");
        Account account2 = new Account(10002,100.1, Account.AccountTypeEnum.CURRENT);
        account2.setIban("NL23INHO2298608058");
        List<Account> accounts = Arrays.asList(bankAccount, account1, account2);
        accounts.forEach((accx) -> {
            this.accountRepository.save(accx);
        });
        /*List<Account> acc = (List)this.accountRepository.findAll();
        PrintStream var10001 = System.out;
        Objects.requireNonNull(var10001);
        acc.forEach(var10001::println);*/
    }


}
