package io.swagger.service;


import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Transaction transaction;

    public TransactionService() {
    }


    public List<Transaction> getFilteredTransactions( String iban, String date, Double maxAmount, Double minAmount) throws Exception {
        LocalDate dayMin;
        LocalDate dayMax;

        if(minAmount == null) minAmount = 0.0;
        if(maxAmount ==  null) maxAmount = Double.MAX_VALUE;
        if(date == null) {
            dayMin = LocalDate.MIN;
            dayMax = LocalDate.now();
        }else{
            dayMin = LocalDate.parse(date);
            dayMax = LocalDate.parse(date);
        }
        if(iban == null) {
            return findBy(minAmount, maxAmount, dayMin, dayMax);
        }

        //return new ResponseEntity<List<Transaction>>(transactionService.findBy(minAmount, maxAmount),HttpStatus.OK);
        List<Transaction> transactions = null;
        transactions = findByIbanAndDatetimeBetweenAndAmountBetween(iban, minAmount, maxAmount, dayMin, dayMax);
        return transactions;

    }

    //get one transaction based on transaction ID.
    public Transaction getSpecificTransaction(Integer transactionId)
    {
        //return transactionRepository.findOne(transactionId);
        return transactionRepository.findById(transactionId).orElseThrow(IllegalArgumentException::new);
    }

    //get all transaction based on userID.
    public List<Transaction> getAllTransactions()
    {
        return (List<Transaction>) transactionRepository.findByOrderBySender();
    }

    public List<Transaction> findByIbanAndDatetimeBetweenAndAmountBetween(String iban, Double min, Double max, LocalDate dateMin, LocalDate dateMax) throws Exception {
        //initialize values
        Account a = accountRepository.findById(iban).orElseThrow(IllegalArgumentException::new); //change to whatever michael uses for @id in the end
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

        System.out.println("we got this far");

        if(!transaction.getTransactionType().equals(Transaction.TransactionTypeEnum.DEPOSIT))
        {
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
        }
        checkTransactionType(transaction);

        System.out.println(transaction);

        transactionRepository.save(transaction);
    }

    //checker if the account has reached the day transaction limit
    public boolean transactionDayLimitChecker(Account Iban)
    {
        LocalDateTime dayMin = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime dayMax = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        List<Transaction> transactionList = transactionRepository.findBySenderEqualsAndDatetimeBetween(Iban, dayMin, dayMax);

        return transactionList.size() < transaction.getSender().getCumulativeTransaction();
    }

    //checker if the transaction is within the range of transaction limit
    public boolean transactionAmountLimitChecker(double amount)
    {
        //range is 0 to 10000.0
        return amount > 0.0 && amount < transaction.getSender().getTransactionAmoutLimit();
    }

    //checker if the request amount will be over the absolute limit of the account
    //change to boolean later
    public boolean transactionAbsoluteLimitChecker(Transaction transaction)
    {
        //get the account info.
        //check diff between balance and transaction amount.
        //then check diff with absolute limit.
        return transaction.getSender().getBalance() - transaction.getAmount() >= transaction.getSender().getAbsoluteLimit();
    }

    public boolean checkIfAccountsExists(Transaction transaction)
    {
        Account sender = accountRepository.findById(transaction.getSender().getIban()).orElseThrow(IllegalArgumentException::new);
        Account recipient = accountRepository.findById(transaction.getRecipient().getIban()).orElseThrow(IllegalArgumentException::new);
        return sender != null && recipient != null;
    }


    public void checkUserPerforming() throws Exception {
       User userPerforming = transaction.getUserPerforming();
       //System.out.println(userPerforming);
       //System.out.println(transaction.getSender().getOwner());
       if(!(userPerforming.getAccessLevel().equals(User.AccessLevelEnum.EMPLOYEE) || userPerforming.equals(transaction.getSender().getOwner()))){
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
            if(sender.getOwner() == recipient.getOwner()){
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
            // change balance of both sender and recipient
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
