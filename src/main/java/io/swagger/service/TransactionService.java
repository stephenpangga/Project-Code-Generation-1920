package io.swagger.service;


import io.swagger.model.Transaction;
import io.swagger.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionService() {
    }


    public Transaction getSpecificTransaction(Integer transactionId)
    {
        return transactionRepository.findOne(transactionId);
    }

    public List<Transaction> findBy(Double min, Double max)
    {
        return transactionRepository.findByAmountBetween(min, max);
    }

    public List<Transaction> getAllTransactions()
    {
        /*
        List<Transaction>  t = transactionRepository.findBySender(null);
        for (Transaction transaction : t) {
            System.out.println(transaction);
        }
        System.out.println(t.size());
        */
        return (List<Transaction>) transactionRepository.findByOrderBySender();
    }

    public void saveTransaction(Transaction transaction)
    {
        //TODO check if the user is the owner of the account or an employee logged in.
        transactionRepository.save(transaction);
    }

}
