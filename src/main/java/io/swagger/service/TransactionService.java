package io.swagger.service;


import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.threeten.bp.*;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Transaction transaction;

    public TransactionService() {
    }


    //get one transaction based on transaction ID.
    public Transaction getSpecificTransaction(Integer transactionId)
    {
        return transactionRepository.findOne(transactionId);
    }

    //get all transaction based on userID.
    public List<Transaction> getAllTransactions()
    {
        return (List<Transaction>) transactionRepository.findByOrderBySender();
    }

    public List<Transaction> findByIbanAndDatetimeBetweenAndAmountBetween(String iban, Double min, Double max, LocalDate dateMin, LocalDate dateMax) throws Exception {
        //initialize values
        Account a = accountRepository.findOne(iban); //change to whatever michael uses for @id in the end
        if(a == null) throw new Exception("Account requested doesn't exist");
        LocalDateTime dayMin = LocalDateTime.of(dateMin, LocalTime.MIN);
        LocalDateTime dayMax = LocalDateTime.of(dateMax, LocalTime.MAX);

        //get transactions from db
        List<Transaction> t = transactionRepository.findByRecipientAndDatetimeBetweenAndAmountBetween(a, dayMin, dayMax, min, max);
        t.addAll(transactionRepository.findBySenderAndDatetimeBetweenAndAmountBetween(a, dayMin, dayMax, min, max));

        return t;
    }

    //filters for Transaction
    public List<Transaction> findBy(Double min, Double max, LocalDate dateMin, LocalDate dateMax)
    {
        LocalDateTime dayMin = LocalDateTime.of(dateMin, LocalTime.MIN);
        LocalDateTime dayMax = LocalDateTime.of(dateMax, LocalTime.MAX);
        return transactionRepository.findByAmountBetweenAndDatetimeBetween(min, max, dayMin, dayMax);
    }

    public void saveTransaction(Transaction transaction) throws Exception {

        this.transaction = transaction;

       /* if(!checkIfAccountsExists(transaction)){
            throw new Exception("sender or recipient doenst exist");
        }*/
        if(!transactionAbsoluteLimitChecker(transaction)){
            throw new Exception("balance will be too low, can't perform transaction");
        }
        if(!transactionDayLimitChecker(transaction.getSender()))
        {
            throw new Exception(" Transaction limit reached");
        }
        if(!transactionAmountLimitChecker(transaction.getAmount()))
        {
            throw new Exception("The amount requested exceeds the maximum amount allowed");
        }
        checkTransactionType(transaction);

        System.out.println(transaction);
        //transaction.setTransactionId(7);
        transactionRepository.save(transaction);
    }

    //checker if the account has reached the day transaction limit
    public boolean transactionDayLimitChecker(Account Iban)
    {
        LocalDateTime dayMin = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime dayMax = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        List<Transaction> transactionList = transactionRepository.findBySenderEqualsAndDatetimeBetween(Iban, dayMin, dayMax);

        if(transactionList.size() < transaction.getCumulativeTransaction())
        {
            return true;
        }
        return false;
    }

    //checker if the transaction is within the range of transaction limit
    public boolean transactionAmountLimitChecker(double amount)
    {
        //range is 0 to 10000.0
        if(amount > 0.0 && amount < transaction.getTransactionAmoutLimit())
        {
            return true;
        }
        return false;
    }

    //checker if the request amount will be over the absolute limit of the account
    //change to boolean later
    public boolean transactionAbsoluteLimitChecker(Transaction transaction)
    {
        //get the account info.
        //check diff between balance and transaction amount.
        //then check diff with absolute limit.
        if(transaction.getSender().getBalance() - transaction.getAmount() >= transaction.getAbsoluteLimit()){
            return true;
        }
        return false;
    }

    public boolean checkIfAccountsExists(Transaction transaction)
    {
        Account sender = null; //= accountRepository.findOne(transaction.getSender().getIban()); - need to fix based on michael's code, call correct @Id
        Account recipient = null; //= accountRepository.findOne(transaction.getRecipient().getIban());
        if(sender!= null && recipient != null)
        {
            return true;
        }
        return false;
    }

    public void checkAccountBalance(Transaction transaction)
    {
        Account sender = transaction.getSender();

        //check the transaction type
        //get account balance
        //check the balance with absolute value
        //if not possible check
    }

    public void checkUserPerforming() throws Exception {
       User userPerforming = transaction.getUserPerforming();
       //if michael fixes his part then do userPerforming.equals(transaction.getSender().getOwner());
       if(!(userPerforming.getAccessLevel().equals(User.AccessLevelEnum.EMPLOYEE) || userPerforming.getId().equals(transaction.getSender().getAuthorId()))){
           throw new Exception ("user is not authorised");
       }
        //check if its employee or owner, return that
        //if(user performing is not owner or employee)
            //throw new exception ("you are not authorised");
    }

    private void changeBalance(Account account, Double amount){
        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);
    }

    private void transferMoney(Account sender, Account recipient, Transaction transaction) throws Exception {
        if(sender.getAccountType().equals(Account.AccountTypeEnum.SAVINGS) || recipient.getAccountType().equals(Account.AccountTypeEnum.SAVINGS)){
            if(sender.getAuthorId().equals(recipient.getAuthorId())){
                changeBalance(sender, transaction.getAmount()*-1);//-1 to turn the value negative.
                changeBalance(recipient, transaction.getAmount());
            }else{
                throw new Exception("Can't do this transaction, savings account owner doesn't match");
            }
        }
        changeBalance(sender, transaction.getAmount()*-1);
        changeBalance(recipient, transaction.getAmount());
    }

    private void withdrawMoney(Account sender, Transaction transaction) throws Exception {
        if(sender.getAccountType().equals(Account.AccountTypeEnum.CURRENT)){
            changeBalance(sender, transaction.getAmount()*-1);
        }else{
            throw new Exception("cannot withdraw directly from a savings account");
        }
    }

    private void depositMoney(Account recipient, Transaction transaction) throws Exception {
        if(recipient.getAccountType().equals(Account.AccountTypeEnum.CURRENT)){
            changeBalance(recipient, transaction.getAmount());
        }else{
            throw new Exception("cannot deposit directly to a savings account");
        }
    }

    public void checkTransactionType(Transaction transaction) throws Exception {
        //check if user performing owns the account or is an employee
        checkUserPerforming();
        Account sender = transaction.getSender();
        Account recipient = transaction.getRecipient();
        if(transaction.getTransactionType().equals(Transaction.TransactionTypeEnum.TRANSFER)){
            // change ballance of both sender and recipient
            // for savings account, check if both accounts have the same owner
            transferMoney(sender, recipient, transaction);
        } else if (transaction.getTransactionType().equals(Transaction.TransactionTypeEnum.WITHDRAW)){
            //check if account is current
            // reduce amount of sender, ignore recipient
            withdrawMoney(sender, transaction);
        } else if (transaction.getTransactionType().equals(Transaction.TransactionTypeEnum.DEPOSIT)){
            //check if its current
            //increase amount of recipient
            depositMoney(recipient, transaction);
        }
    }
}
/*
 * todo
 *  checker for account balance - done
 *  a method to update balance - done
 *  reduce balance method - done
 *  add balance method - done
 *  check if the user is the owner of the account or an employee logged in. - done
 *  update balance from account - done
 *  get account type
 *  withdraw method
 *  deposit method
 *  perform transaction based on account type, current or savings
 */
