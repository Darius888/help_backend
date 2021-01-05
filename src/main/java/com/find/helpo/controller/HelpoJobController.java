package com.find.helpo.controller;

import com.find.helpo.model.HelpoJob;
import com.find.helpo.model.HelpoJobDTO;
import com.find.helpo.service.HelpoJobService;
import com.find.helpo.service.ProfilePhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/job")
public class HelpoJobController {

    @Autowired
    private HelpoJobService helpoJobService;

    @Autowired
    private ProfilePhotoService fileService;

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
    private String updateHelpoJobTitle(@RequestParam String jobTitle)
    {
        return helpoJobService.updateHelpoJobTitle(jobTitle);
    }

    @RequestMapping(value = "/deleteHelpoJobBy", method = RequestMethod.DELETE)
    private String deleteHelpoJobByJobTitleAndJobOwnerId(@RequestParam String jobTitle, @RequestParam Integer jobOwnerId)
    {
        return helpoJobService.deleteHelpoJobByHelpoJobTitleAndHelpoJobOwnerId(jobTitle, jobOwnerId);
    }

    @RequestMapping(value = "/deleteHelpoJob", method = RequestMethod.DELETE)
    private String deleteHelpoJob(@RequestBody HelpoJobDTO helpoJobDTO)
    {
        System.out.println("AAAAAAAA" + helpoJobDTO.getHelpoJobID());
        return helpoJobService.deleteHelpoJob(helpoJobDTO);
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        fileService.uploadFile(file);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @PostMapping("/uploadFiles")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes) {

        Arrays.asList(files)
                .stream()
                .forEach(file -> fileService.uploadFile(file));

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded all files!");

        return "redirect:/";
    }

}
