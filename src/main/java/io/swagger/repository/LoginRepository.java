package io.swagger.repository;

import io.swagger.model.Login;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Login, String> {

    @Query("SELECT l FROM Login l WHERE l.user.email = ?")
    Login findByEmail(String email);
}
