package com.find.helpo.repository;

import com.find.helpo.model.AverageScore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface AverageScoreRepository extends CrudRepository<AverageScore, Integer> {

        AverageScore findByAverageScoreID(Integer averageScoreID);

        Boolean existsByAverageScoreID(Integer averageScoreID);

}
