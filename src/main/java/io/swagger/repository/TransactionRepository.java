package io.swagger.repository;

import io.swagger.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAll();
    List<Transaction> findByOrderBySender();
    List<Transaction> findBySender(String sender);
    List<Transaction> findByAmountBetween(Double min, Double max);
}
