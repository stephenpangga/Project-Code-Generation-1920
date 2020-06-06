package io.swagger.configuration;

import io.swagger.model.Transaction;
import io.swagger.model.User;
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


    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        loadTransactions();
        loadUsers();
    }

    /*** save the datas here ***/
    /* http://localhost:8080/api/h2-console */

    /*^^ the database link**/

    public void loadUsers()
    {
        List<User> userList = Arrays.asList(
                new User("stepehn@gmail.com",
                        "password",
                        "Stephen",
                        "Pangga", User.AccessLevelEnum.EMPLOYEE
                ),
                new User("frances@gmail.com",
                "frances",
                "Frances",
                "Agasino", User.AccessLevelEnum.CUSTOMER
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


}
