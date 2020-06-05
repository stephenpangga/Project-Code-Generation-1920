package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> GetAllAccounts() {
        List<Account> accounts = (List<Account>) accountRepository.findAll();
        accounts.forEach(acc->acc.getCurrency());
        return  accounts;
    }

    public Account CreateAccount(Account newAccount) {
        accountRepository.save(newAccount);
        return newAccount;
    }

    public Account UpdateAccount(Account account, String IBAN) {
        Account edite_Account = null;
        Optional<Account> originalAccount = Optional.ofNullable(accountRepository.findOne(IBAN));
        if (originalAccount.isPresent()) {
             edite_Account = originalAccount.get();
           edite_Account.setAccountType(account.getAccountType());
            accountRepository.save(edite_Account);
        }
        return  edite_Account;
    }
}
