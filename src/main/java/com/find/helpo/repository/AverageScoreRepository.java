package com.find.helpo.repository;

import com.find.helpo.model.AverageScore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AverageScoreRepository extends CrudRepository<AverageScore, Integer> {

        AverageScore findByScoreID(Integer averageScoreID);

        Boolean existsByScoreReceiverID(Integer averageScoreID);

        List<AverageScore> findByScoreReceiverID(Integer scoreReceiverID);

        String deleteByScoreOwnerID(Integer userID);

}
