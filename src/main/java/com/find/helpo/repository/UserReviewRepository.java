package com.find.helpo.repository;

import com.find.helpo.model.UserReview;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserReviewRepository extends CrudRepository<UserReview, Integer> {

    UserReview findByUserReviewID(Integer averageScoreID);

    Boolean existsByReviewReceiverID(Integer averageScoreID);

    List<UserReview> findByReviewReceiverID(Integer scoreReceiverID);

    String deleteByReviewOwnerID(Integer userID);

}
