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
    AccountRepository repository;

    public List<Account> GetAllAccounts() {
        List<Account> accounts = (List<Account>) repository.findAll();
        return  accounts;
    }
    public Account AddAccount(Integer userId, String StringAccType) {
        Account.AccountTypeEnum accountType = Account.AccountTypeEnum.valueOf(StringAccType);
        Account acc = new Account();
        repository.save(acc);
        return acc;
    }
    public void updateAccount(Account account, Integer id) {
        Optional<Account> carOptional = Optional.ofNullable(repository.findOne(id));
        if (carOptional.isPresent()) {
            Account _account = carOptional.get();
            _account.setAccountType(account.getAccountType());
            repository.save(_account);
        }
    }
}
