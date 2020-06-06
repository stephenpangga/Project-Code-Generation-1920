package config;

import io.swagger.DAL.AccountRepository;
import io.swagger.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class StartupRunner implements ApplicationRunner {
    private AccountRepository repository;
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        List<Account>accounts =  Arrays.asList(
        );

        accounts.forEach(account -> repository.save(account));
        repository.findAll().forEach(System.out::println);

    }
}
