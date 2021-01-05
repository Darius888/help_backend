package com.find.helpo.repository;

import com.find.helpo.model.HelpoJob;
import com.find.helpo.model.HelpoJobDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


@Repository
public interface HelpoJobRepository extends CrudRepository<HelpoJob, Integer> {

    List<HelpoJob> findAll();

    HelpoJob findByJobTitle(String helpoJobTitle);

    List<HelpoJob> findByJobOwnerID(Integer helpoJobOwnerID);

    Integer deleteByHelpoJobID(Integer helpoJobID);

    Integer deleteByJobTitleAndJobOwnerID(String helpoJobTitle, Integer helpoJobOwnerID);

    Boolean existsByHelpoJobID(Integer helpoJobID);

    Boolean existsByJobTitle(String helpoJobTitle);

    Boolean existsByJobDescription(String helpJobDescription);

    Boolean existsByJobTitleAndJobDescription(String helpoJobTitle, String helpJobDescription);

    Boolean existsByJobTitleAndJobOwnerID(String helpoJobTitle, Integer helpoJobOwnerID);




}
