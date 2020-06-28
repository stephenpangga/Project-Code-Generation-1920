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
        List<Account> accounts =  accountRepository.findAll();
        return  accounts;
    }

    public Account CreateAccount(Account newAccount) {
        accountRepository.save(newAccount);
        return newAccount;
    }

    public Account UpdateAccount(Account account, String IBAN) {
        Account editeAccount = null;
        Optional<Account> originalAccount = Optional.ofNullable(accountRepository.findById(IBAN).orElseThrow(IllegalArgumentException::new));
        if (originalAccount.isPresent()) {
             editeAccount = originalAccount.get();
           editeAccount.setAccountType(account.getAccountType());
            accountRepository.save(editeAccount);
        }
        return  editeAccount;
    }

    public void DeleteAccount(String IBAN) {
        accountRepository.deleteById(IBAN);
    }

    public Account GetAccount(String IBAN) {
        return accountRepository.findById(IBAN).orElseThrow(IllegalArgumentException::new);
    }
}
