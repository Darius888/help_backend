package com.find.helpo.controller;

import com.find.helpo.DTO.HelpoJobDTO;
import com.find.helpo.DTO.HelpoJobPhotoDTO;
import com.find.helpo.config.JwtTokenUtil;
import com.find.helpo.model.HelpoJob;
import com.find.helpo.model.HelpoJobPhoto;
import com.find.helpo.service.HelpoJobPhotoService;
import com.find.helpo.service.HelpoJobService;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
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


    @RequestMapping(value = "/createHelpoJob", method = RequestMethod.POST, consumes = {"application/octet-stream", "multipart/form-data"})
    private String createNewJob(@RequestPart("jobPhotos") MultipartFile[] jobPhotos, @NotNull @NotBlank @RequestPart("helpoJobDTO") HelpoJobDTO helpoJobDTO, HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        Cookie[] cookies = request.getCookies();
        if(cookies.length !=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    if ((jwtTokenUtil.validateToken(cookie.getValue(), jwtTokenUtil.getUserEmailFromToken(cookie.getValue()))))
                        return helpoJobService.createNewJob(jobPhotos, helpoJobDTO, jwtTokenUtil.getUserEmailFromToken(cookie.getValue()), redirectAttributes);
                }
            }
        }
        return "Failed to create new job";
    }

    @RequestMapping(value = "/getAllHelpoJobs", method = RequestMethod.GET)
    private List<HelpoJob> getAllHelpoJobS()
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

//    @RequestMapping(value = "/uploadHelpoJobPhotos",
//                    method = RequestMethod.POST,
//                    consumes = {"multipart/form-data"})
//    public String multipleFileUpload(@RequestPart("files") MultipartFile[] files, HttpServletRequest request,
//                                     RedirectAttributes redirectAttributes) {
//        return helpoJobPhotoService.uploadNewHelpoJobPhotos(files, email, redirectAttributes);
//    }
}
