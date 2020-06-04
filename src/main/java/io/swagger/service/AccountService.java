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
        return  accounts;
    }


    public Account AddAccount(Integer UserID,String StringAccType) {
        Account.AccountTypeEnum accountType = Account.AccountTypeEnum.valueOf(StringAccType);
        Account acc = new Account( )
                .accountType(accountType)
                .authorId(UserID);
        accountRepository.save(acc);
        return acc;
    }

    public void updateAccount(Account account, Integer id) {

        /*Optional<Account> OriginalAccount = Optional.ofNullable(accountRepository.findOne(id));
        if (OriginalAccount.isPresent()) {
            Account Edited_account = OriginalAccount.get();
           Edited_account.setAccountType(account.getAccountType());
            accountRepository.save(Edited_account);
        }*/
    }
}
