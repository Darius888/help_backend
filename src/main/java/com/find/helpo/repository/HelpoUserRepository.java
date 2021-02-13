package com.find.helpo.repository;

import com.find.helpo.model.HelpoUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpoUserRepository extends CrudRepository<HelpoUser, Integer> {

    HelpoUser findByHelpoUserID(Integer helpoUserID);

    HelpoUser findByEmail(String helpoUserEmail);

    Boolean existsByEmail(String helpoUserEmail);

}
