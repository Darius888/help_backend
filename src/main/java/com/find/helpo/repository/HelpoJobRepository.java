package com.find.helpo.repository;

import com.find.helpo.model.HelpoJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HelpoJobRepository extends CrudRepository<HelpoJob, Integer> {

    List<HelpoJob> findAll();

    HelpoJob findByJobTitle(String helpoJobTitle);

    List<HelpoJob> findByJobOwnerID(Integer helpoJobOwnerID);

    HelpoJob findByHelpoJobID(Integer helpoJobID);

    Integer deleteByHelpoJobID(Integer helpoJobID);

    Integer deleteByJobTitleAndJobOwnerID(String helpoJobTitle, Integer helpoJobOwnerID);

    Boolean existsByHelpoJobID(Integer helpoJobID);

    Boolean existsByJobTitle(String helpoJobTitle);

    Boolean existsByJobDescription(String helpJobDescription);

    Boolean existsByJobTitleAndJobDescription(String helpoJobTitle, String helpJobDescription);

    Boolean existsByJobTitleAndJobOwnerID(String helpoJobTitle, Integer helpoJobOwnerID);




}
