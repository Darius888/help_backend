package com.find.helpo.controller;

import com.find.helpo.DTO.HelpoJobDTO;
import com.find.helpo.DTO.HelpoJobPhotoDTO;
import com.find.helpo.config.JwtTokenUtil;
import com.find.helpo.model.HelpoJob;
import com.find.helpo.model.HelpoJobGet;
import com.find.helpo.model.HelpoJobPhoto;
import com.find.helpo.service.CookieValidationService;
import com.find.helpo.service.HelpoJobPhotoService;
import com.find.helpo.service.HelpoJobService;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/job")
public class HelpoJobController {

    @Autowired
    private HelpoJobService helpoJobService;

    @Autowired
    private HelpoJobPhotoService helpoJobPhotoService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CookieValidationService cookieValidationService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/createHelpoJob")
    private String createNewJob(@RequestPart("helpoJobPhotos") MultipartFile[] helpoJobPhotos,@RequestPart("helpoJobDTO") HelpoJobDTO helpoJobDTO, RedirectAttributes redirectAttributes, HttpServletRequest request)
    {

       if(cookieValidationService.validateCookieToken(request.getCookies()))
       {
           return helpoJobService.createNewJob(helpoJobPhotos, helpoJobDTO, jwtTokenUtil.getUserEmailFromToken(cookieValidationService.getCookieWithTokenValue(request.getCookies()).getValue()), redirectAttributes);
       }
           return "Issue with validation";
    }

    @RequestMapping(value = "/getAllHelpoJobs", method = RequestMethod.GET)
    private List<HelpoJobGet> getAllHelpoJob()
    {
        return helpoJobService.getAllHelpoJobs();
    }

    @RequestMapping(value = "/getAllHelpoJobsByOwnerId", method = RequestMethod.GET)
    private List<HelpoJob> getAllHelpoJobsByOwnerID(@RequestParam Integer jobOwnerId)
    {
        return helpoJobService.getHelpoJobsByOwnerID(jobOwnerId);
    }

    @RequestMapping(value = "/getHelpoJobBy", method = RequestMethod.GET)
    private HelpoJob getHelpoJobByJobTitle(@RequestParam String jobTitle)
    {
        return helpoJobService.getHelpoJobByHelpoJobTitle(jobTitle);
    }

    //TO-DO custom update for all fields
    @RequestMapping(value = "/updateHelpoJobs", method = RequestMethod.PUT)
    private String updateHelpoJobTitle(@RequestBody HelpoJobDTO helpoJobDTO)
    {
        return helpoJobService.updateHelpoJob(helpoJobDTO);
    }

    @RequestMapping(value = "/deleteHelpoJobBy", method = RequestMethod.DELETE)
    private String deleteHelpoJobByJobTitleAndJobOwnerId(@RequestParam String jobTitle,
                                                         @RequestParam Integer jobOwnerId)
    {
        return helpoJobService.deleteHelpoJobByHelpoJobTitleAndHelpoJobOwnerId(jobTitle, jobOwnerId);
    }

    @RequestMapping(value = "/deleteHelpoJob", method = RequestMethod.DELETE)
    private String deleteHelpoJob(@RequestBody HelpoJobDTO helpoJobDTO)
    {
        return helpoJobService.deleteHelpoJob(helpoJobDTO);
    }
}
