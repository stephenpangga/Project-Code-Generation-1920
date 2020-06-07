package io.swagger.repository;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    List<Transaction> findAll();
    List<Transaction> findByOrderBySender();

    List<Transaction> findBySenderAndDatetimeBetweenAndAmountBetween(Account sender, LocalDateTime dayMin, LocalDateTime dayMax, Double min, Double max);
    List<Transaction> findByRecipientAndDatetimeBetweenAndAmountBetween(Account recipient, LocalDateTime dayMin, LocalDateTime dayMax, Double min, Double max);

    //for the amount filter.
    //List<Transaction> findBySender(String sender);
    List<Transaction> findByAmountBetweenAndDatetimeBetween(Double min, Double max, LocalDateTime dayMin, LocalDateTime dayMax);

    //limit checkers
    List<Transaction> findBySenderEqualsAndDatetimeBetween(Account userIban, LocalDateTime dayMin, LocalDateTime dayMax);
}
