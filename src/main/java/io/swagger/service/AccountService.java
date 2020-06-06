package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan("module-service")
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> GetAllAccounts() {
        List<Account> accounts = (List<Account>) accountRepository.findAll();
        accounts.forEach(acc->acc.getCurrency());
        return  accounts;
    }

    public Account CreateAccount(Account newAccount) {
        newAccount.setBalance(0.0);
        accountRepository.save(newAccount);
        return newAccount;
    }

    public Account UpdateAccount(Account account, String IBAN) {
        Account editeAccount = null;
        Optional<Account> originalAccount = Optional.ofNullable(accountRepository.findOne(IBAN));
        if (originalAccount.isPresent()) {
             editeAccount = originalAccount.get();
           editeAccount.setAccountType(account.getAccountType());
            accountRepository.save(editeAccount);
        }
        return  editeAccount;
    }
}
