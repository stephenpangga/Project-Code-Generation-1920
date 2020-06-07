package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@ComponentScan("module-service")
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> GetAllAccounts() {
        List<Account> accounts = (List<Account>) accountRepository.findAll();
        return  accounts;
    }

    public Account CreateAccount(Account newAccount) {
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

    public void DeleteAccount(String IBAN) {
        accountRepository.delete(IBAN);
    }
/*
    public List<Account> GetCustomerAccounts(int customerID) {
        List<Account> accounts = new java.util.ArrayList<>(Collections.emptyList());

        for (Account account : accountRepository.findAll()) {
            if (account.getAuthorId() == customerID) {
                accounts.add(account);
            }
        }
        
        return  accounts;
    }
*/
    public Account GetAccount(String IBAN) {
        return accountRepository.findOne(IBAN);
    }
}
