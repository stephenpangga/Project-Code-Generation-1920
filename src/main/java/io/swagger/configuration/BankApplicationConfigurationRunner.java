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
    private AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        loadTransactions();
        loadUsers();
        loadAccounts();
    }

    /*** save the datas here ***/
    /* http://localhost:8080/api/h2-console */

    /*^^ the database link**/

    public void loadUsers()
    {
        List<User> userList = Arrays.asList(
                new User("Stephen",
                        "Pangga",
                        "stephen@gmail.com",
                        "password", User.AccessLevelEnum.EMPLOYEE
                ),
                new User("Frances",
                "Agasino",
                "frances@gmail.com",
                "password", User.AccessLevelEnum.CUSTOMER
                ),
                new User("Sisa",
                        "Mokranova",
                        "sisa@gmail.com",
                        "somethingstrongerthanpassword", User.AccessLevelEnum.CUSTOMER
                )
        );
        for(User user: userList)
        {
            userRepository.save(user);
        }
    }


    public void loadTransactions()
    {
        Account account1 = new Account(5,"NL23INHO2298608059",100.1, Account.AccountTypeEnum.CURRENT);
        Account account2 = new Account(2,"NL23INHO2298608058",100.1, Account.AccountTypeEnum.CURRENT);
        List<Transaction> transactionList = Arrays.asList(
                new Transaction(account1,
                        account2,
                        503.73,
                        Transaction.TransactionTypeEnum.TRANSFER,
                        1,
                        LocalDateTime.now()),
                new Transaction(account1,
                        account2,
                        502.73,
                        Transaction.TransactionTypeEnum.TRANSFER,
                        1,
                        LocalDateTime.now()),
                new Transaction(account2,
                        account1,
                        501.73,
                        Transaction.TransactionTypeEnum.WITHDRAW,
                        1,
                        LocalDateTime.now())
        );

        for (Transaction transaction : transactionList) {
            transactionRepository.save(transaction);
        }

        for (Transaction transaction : transactionRepository.findAll()) {
            System.out.println(transaction);
        }
    }

    public void loadAccounts(){
        List<Account> accounts = Arrays.asList(
                new Account().authorId(1).accountType(Account.AccountTypeEnum.SAVINGS),
                new Account().authorId(3).accountType(Account.AccountTypeEnum.CURRENT)
        );

        accounts.forEach(acc->accountRepository.save(acc));
        List<Account>acc = (List<Account>) accountRepository.findAll();
        acc.forEach(System.out::println);
    }

}
