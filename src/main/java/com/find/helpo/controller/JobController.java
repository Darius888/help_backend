package com.find.helpo.controller;

import com.find.helpo.model.HelpoJob;
import com.find.helpo.model.HelpoJobDTO;
import com.find.helpo.repository.HelpoJobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/job")
public class JobController {

    @Autowired
    HelpoJobRepository helpoJobRepository;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    private String createNewJob(@RequestBody HelpoJobDTO helpoJobDTO)
    {

       ModelMapper modelMapper = new ModelMapper();
       HelpoJob helpoJob = modelMapper.map(helpoJobDTO, HelpoJob.class);

       helpoJobRepository.save(helpoJob);
       return "ok";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    private String deleteExistingJob(@RequestParam String jobTitle)
    {
//

//        System.out.println("AAAAAAAAAAA" + helpoJobRepository.findAll().toString());
       // jobRepository.delete(job);
        return "Deleted";
    }

}
