package io.swagger.service;


import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.threeten.bp.*;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

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

    //filters for Transaction

    public List<Transaction> findBy(Double min, Double max)
    {
        return transactionRepository.findByAmountBetween(min, max);
    }

    public void saveTransaction(Transaction transaction) throws Exception {

        this.transaction = transaction;

        if(!transactionDayLimitChecker(transaction.getSender()))
        {
            throw new Exception(" Transaction limit reached");
        }
        if(!transactionAmountLimitChecker(transaction.getAmount()))
        {
            throw new Exception("The amount requested exceeds the maximum amount allowed");
        }
        transactionRepository.save(transaction);
    }

    //checker if the account has reached the day transaction limit
    public boolean transactionDayLimitChecker(String Iban)
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
        return false;
    }


    /*
     * todo
     *  checker for account balance
     *  a method to update balance
     *  reduce balance method
     *  add balance method
     *  check if the user is the owner of the account or an employee logged in.
     *  update balance from account
     *  get account type
     *  withdraw method
     *  deposit method
     *  perform transaction based on account type, current or savings
     */

    public void checkAccountBalance()
    {
        //check the transaction type
        //get account balance
        //check the balance with absolute value
        //if not possible check
    }
}
