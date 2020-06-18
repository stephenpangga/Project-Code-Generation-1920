package io.swagger.repository;

import io.swagger.model.Login;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Login, String> {

    /*
    **added `email` in your query, not sure if this fixes it, since it was giving error when updated the spring boot and java version to 11.
     */
    @Query("SELECT l FROM Login l WHERE l.user.email = :email")
    Login findByEmail(String email);
}
