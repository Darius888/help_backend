package com.find.helpo.controller;

import com.find.helpo.DTO.AverageScoreDTO;
import com.find.helpo.model.AverageScore;
import com.find.helpo.service.AverageScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/score")
public class AverageScoreController {

    @Autowired
    private AverageScoreService averageScoreService;

    @RequestMapping(path = "/rate", method = RequestMethod.POST)
    public String addNewRating(@RequestBody AverageScoreDTO averageScoreDTO)
    {
        return averageScoreService.addNewScore(averageScoreDTO);
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public Float getExistingAverageRating(@RequestParam Integer userID)
    {
        return averageScoreService.getAverageScoreForUser(userID);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public String updateExistingRating(AverageScore averageScore)
    {
        return averageScoreService.updateExistingScore(averageScore);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public String deleteExistingRating(@RequestParam Integer userID)
    {
        return averageScoreService.deleteExistingScore(userID);
    }

}
