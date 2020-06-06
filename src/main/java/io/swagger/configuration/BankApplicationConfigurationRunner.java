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
        loadTransactions();
        loadUsers();
        LoadAccounts();
        LoadLogins();
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
                new Transaction("NL01INHO1c",
                        "NL02INHO2",
                        503.73,
                        Transaction.TransactionTypeEnum.TRANSFER,
                        1,
                        LocalDateTime.now()),
                new Transaction("NL01INHO1b",
                        "NL02INHO2",
                        502.73,
                        Transaction.TransactionTypeEnum.TRANSFER,
                        1,
                        LocalDateTime.now()),
                new Transaction("NL01INHO1a",
                        "NL02INHO2",
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
  public void LoadAccounts(){
        List<Account> accounts = Arrays.asList(
          new Account().authorId(1).accountType(Account.AccountTypeEnum.SAVINGS),
        new Account().authorId(3).accountType(Account.AccountTypeEnum.CURRENT)
      );
        accounts.forEach(acc->accountRepository.save(acc));
        List<Account>acc = (List<Account>) accountRepository.findAll();
        acc.forEach(System.out::println);
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
