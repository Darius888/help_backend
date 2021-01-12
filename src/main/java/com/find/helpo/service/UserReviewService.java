package com.find.helpo.service;

import com.find.helpo.DTO.UserReviewDTO;
import com.find.helpo.model.UserReview;
import com.find.helpo.repository.UserReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserReviewService {

    @Autowired
    private UserReviewRepository userReviewRepository;

    public String addNewReview(UserReviewDTO userReviewDTO)
    {
        if(userReviewRepository.existsByReviewReceiverID(userReviewDTO.getReviewReceiverID()))
        {
            return "Such score already exists from this user";
        } else
        {
            ModelMapper modelMapper = new ModelMapper();
            UserReview userReview = modelMapper.map(userReviewDTO, UserReview.class);
            userReviewRepository.save(userReview);
            return "New score saved successfully";
        }
    }

    public List<UserReview> getUserReviews(Integer userID)
    {
        if(userReviewRepository.existsByReviewReceiverID(userID));
        {
            return userReviewRepository.findByReviewReceiverID(userID);
        }
    }

    public String updateExistingUserReview(UserReview userReview)
    {
        if(!userReviewRepository.existsByReviewReceiverID(userReview.getUserReviewID()))
        {
            return "Score you are trying to update, does not exist";
        } else
        {
            UserReview updatedUserReview = userReviewRepository.findByUserReviewID(userReview.getUserReviewID());
            updatedUserReview.setUserReview(userReview.getUserReview());
            userReviewRepository.save(updatedUserReview);
            return "Updated score saved successfully";
        }
    }

    public String deleteExistingUserReview(Integer userID)
    {
        if(!userReviewRepository.existsByReviewReceiverID(userID))
        {
            return "Score you are trying to delete, does not exist";
        } else
        {
            userReviewRepository.deleteByReviewOwnerID(userID);
            return "Score deleted successfully";
        }
    }

}
