package io.swagger.configuration;

import io.swagger.model.Account;
import io.swagger.model.Login;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.LoginRepository;
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
    @Autowired
    LoginRepository loginRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        loadUsers();
        LoadAccounts();
        loadTransactions();
        LoadLogins();
    }

    /*** save the datas here ***/
    /* http://localhost:8080/api/h2-console */

    /*^^ the database link**/

    public void loadUsers()
    {
        List<User> userList = Arrays.asList(
                new User("stephen@gmail.com",
                        "password",
                        "Stephen",
                        "Pangga", User.AccessLevelEnum.EMPLOYEE
                ),
                new User("frances@gmail.com",
                "frances",
                "Frances",
                "Agasino", User.AccessLevelEnum.CUSTOMER
                ),
                new User("Sisa",
                        "Mokranova",
                        "sisa@gmail.com",
                        "somethingstrongerthanpassword", User.AccessLevelEnum.CUSTOMER
                ),
                new User (
                        "625242@student.inholland.nl",
                        "Thomas",
                        "de Joode",
                        "admin",
                        User.AccessLevelEnum.CUSTOMER
                ),
                new User (
                        "629860@student.inholland.nl",
                        "Stephen",
                        "Pangga",
                        "admin",
                        User.AccessLevelEnum.EMPLOYEE
                ),
                new User("sisa@gmail.com",
                        "somethingstrongerthanpassword",
                        "Sisa",
                        "Mokranova", User.AccessLevelEnum.CUSTOMER
                )
        );
        for(User user: userList)
        {
            userRepository.save(user);
        }
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
                  new Account(100.0, users.get(0), Account.AccountTypeEnum.CURRENT),
                  new Account( 10000.0, users.get(0), Account.AccountTypeEnum.SAVINGS),
                  new Account(100.1, users.get(1), Account.AccountTypeEnum.CURRENT),
                  new Account(1058.1, users.get(2), Account.AccountTypeEnum.CURRENT)
          );
          accounts.forEach(acc->accountRepository.save(acc));
          List<Account>acc = (List<Account>) accountRepository.findAll();
          acc.forEach(System.out::println);

            /*
              List<Account> accounts = Arrays.asList(
          new Account(2, 0.0,Account.AccountTypeEnum.CURRENT),
                      new Account(2, 0.0,Account.AccountTypeEnum.SAVINGS)
                      );
          accounts.forEach(acc->accountRepository.save(acc));
            List<Account>acc = (List<Account>) accountRepository.findAll();
            acc.forEach(System.out::println);

             */

    }

  public void LoadLogins() {
        List<Login> logins = Arrays.asList(
                new Login().token("36k1tYIowCWI6svk6aCMgBba9FINxutq").user(userRepository.findAll().get(0)),
                new Login().token("RmSh17nho7f7vYJ66tJnOke1GJ2r8tXT").user(userRepository.findAll().get(1))
        );
        logins.forEach(login -> loginRepository.save(login));
        List<Login> login = (List<Login>) loginRepository.findAll();
        login.forEach(System.out::println);
  }

}
