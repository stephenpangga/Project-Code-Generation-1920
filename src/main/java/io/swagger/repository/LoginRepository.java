package io.swagger.repository;

import io.swagger.model.InlineResponse200;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<InlineResponse200, String> {
}
