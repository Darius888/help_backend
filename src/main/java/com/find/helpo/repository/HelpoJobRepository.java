package com.find.helpo.repository;

import com.find.helpo.model.HelpoJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HelpoJobRepository extends CrudRepository<HelpoJob, Integer> {

//    Job findJobByTitle(String jobTitle);
}
