package com.find.helpo.service;

import com.find.helpo.model.AverageScore;
import com.find.helpo.repository.AverageScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AverageScoreService {

    @Autowired
    private AverageScoreRepository averageScoreRepository;

    public String addNewScore(AverageScore averageScore)
    {
        if(averageScoreRepository.existsByAverageScoreID(averageScore.getAverageScoreID()))
        {
            return "Such score already exists from this user";
        } else
        {
            averageScoreRepository.save(averageScore);
            return "New score saved successfully";
        }
    }

    public String updateExistingScore(AverageScore averageScore)
    {
        if(!averageScoreRepository.existsByAverageScoreID(averageScore.getAverageScoreID()))
        {
            return "Score you are trying to update, does not exist";
        } else
        {
            AverageScore updatedAverageScore = averageScoreRepository.findByAverageScoreID(averageScore.getAverageScoreID());
            updatedAverageScore.setUserScore(averageScore.getUserScore());
            averageScoreRepository.save(updatedAverageScore);
            return "Updated score saved successfully";
        }
    }


}
