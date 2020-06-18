package io.swagger.repository;

import io.swagger.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

    List<Account> findAll();
    //need to make a code query for removing accounts
    void deleteById(String ID);
}
