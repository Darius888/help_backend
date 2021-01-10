package com.find.helpo.controller;

import com.find.helpo.DTO.UserReviewDTO;
import com.find.helpo.model.UserReview;
import com.find.helpo.service.UserReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/review")
public class UserReviewController {

    @Autowired
    private UserReviewService userReviewService;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String addNewReview(@RequestBody UserReviewDTO userReviewDTO)
    {
        return userReviewService.addNewReview(userReviewDTO);
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public List<UserReview> getExistingReviews(@RequestParam Integer userID)
    {
        return userReviewService.getUserReviews(userID);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public String updateExistingReview(UserReview userReview)
    {
        return userReviewService.updateExistingUserReview(userReview);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public String deleteExistingReview(@RequestParam Integer userID)
    {
        return userReviewService.deleteExistingUserReview(userID);
    }

}
