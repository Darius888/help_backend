package com.find.helpo.repository;

import com.find.helpo.model.Helper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelperRepository extends CrudRepository<Helper, Long> {

    Helper findByEmail(String email);
    Boolean existsByEmail(String email);

}
