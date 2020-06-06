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
        LoadAccounts();
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
        List<Account> a = accountRepository.findAll();
        List<User> users = userRepository.findAll();

        Account account1 = a.get(0);
        Account account2 = a.get(1);

        User u = users.get(0);


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
  public void LoadAccounts(){
        List<User> users = userRepository.findAll();
        User bankUser = users.get(0);
        User stephen= users.get(0);
        User dogOwner = users.get(0);

            List<Account> accounts = Arrays.asList(
                new Account("NL01INHO0000000001", 100.0, users.get(0), Account.AccountTypeEnum.CURRENT),
                new Account("NL01INHO0000000002", 100.0, users.get(0), Account.AccountTypeEnum.SAVINGS),
                new Account("NL01INHO0000000078", 100.1, users.get(1), Account.AccountTypeEnum.CURRENT),
                new Account("NL01INHO0000000895",1058.1, users.get(2), Account.AccountTypeEnum.CURRENT)
            );
        accounts.forEach(acc->accountRepository.save(acc));
        List<Account>acc = (List<Account>) accountRepository.findAll();
        acc.forEach(System.out::println);
  }
}
