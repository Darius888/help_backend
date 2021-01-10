package com.find.helpo.service;

import com.find.helpo.DTO.AverageScoreDTO;
import com.find.helpo.model.AverageScore;
import com.find.helpo.model.HelpoJob;
import com.find.helpo.repository.AverageScoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AverageScoreService {

    @Autowired
    private AverageScoreRepository averageScoreRepository;

    public String addNewScore(AverageScoreDTO averageScoreDTO)
    {
        if(averageScoreRepository.existsByScoreReceiverID(averageScoreDTO.getScoreID()))
        {
            return "Such score already exists from this user";
        } else
        {
            ModelMapper modelMapper = new ModelMapper();
            AverageScore averageScore = modelMapper.map(averageScoreDTO, AverageScore.class);
            averageScoreRepository.save(averageScore);
            return "New score saved successfully";
        }
    }

    public Float getAverageScoreForUser(Integer userID)
    {
        if(averageScoreRepository.existsByScoreReceiverID(userID));
        {
            float avg = 0;
            ArrayList<AverageScore> allScoresForUser = (ArrayList<AverageScore>) averageScoreRepository.findByScoreReceiverID(userID);
            for(AverageScore score: allScoresForUser)
            {
                avg += score.getUserScore();
            }
            return avg/allScoresForUser.size();
        }
    }

    public String updateExistingScore(AverageScore averageScore)
    {
        if(!averageScoreRepository.existsByScoreReceiverID(averageScore.getScoreID()))
        {
            return "Score you are trying to update, does not exist";
        } else
        {
            AverageScore updatedAverageScore = averageScoreRepository.findByScoreID(averageScore.getScoreID());
            updatedAverageScore.setUserScore(averageScore.getUserScore());
            averageScoreRepository.save(updatedAverageScore);
            return "Updated score saved successfully";
        }
    }

    public String deleteExistingScore(Integer userID)
    {
        if(!averageScoreRepository.existsByScoreReceiverID(userID))
        {
            return "Score you are trying to delete, does not exist";
        } else
        {
            averageScoreRepository.deleteByScoreOwnerID(userID);
            return "Score deleted successfully";
        }
    }


}
