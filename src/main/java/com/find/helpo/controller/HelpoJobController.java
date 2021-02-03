package com.find.helpo.controller;

import com.find.helpo.DTO.HelpoJobDTO;
import com.find.helpo.DTO.HelpoJobPhotoDTO;
import com.find.helpo.model.HelpoJob;
import com.find.helpo.model.HelpoJobPhoto;
import com.find.helpo.service.HelpoJobPhotoService;
import com.find.helpo.service.HelpoJobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/job")
public class HelpoJobController {


    @Autowired
    private HelpoJobService helpoJobService;

    @Autowired
    private HelpoJobPhotoService helpoJobPhotoService;


    @RequestMapping(value = "/createHelpoJob", method = RequestMethod.POST)
    private String createNewJob(@RequestBody HelpoJobDTO helpoJobDTO)
    {
       return helpoJobService.createNewJob(helpoJobDTO);
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

    @RequestMapping(value = "/uploadHelpoJobPhotos",
                    method = RequestMethod.POST,
                    consumes = {"multipart/form-data"})
    public String multipleFileUpload(@ModelAttribute ArrayList<HelpoJobPhotoDTO> helpoJobPhotoDTOS,
                                     RedirectAttributes redirectAttributes) {
        return helpoJobPhotoService.uploadNewHelpoJobPhotos(helpoJobPhotoDTOS, redirectAttributes);
    }




}
