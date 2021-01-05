package com.find.helpo.service;


import com.find.helpo.model.HelpoJob;
import com.find.helpo.model.HelpoJobDTO;
import com.find.helpo.repository.HelpoJobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class HelpoJobService {

    @Autowired
    private HelpoJobRepository helpoJobRepository;

    public String createNewJob(HelpoJobDTO helpoJobDTO)
    {
        if(helpoJobRepository.existsByJobTitleAndJobDescription(helpoJobDTO.getJobTitle(),helpoJobDTO.getJobDescription()))
        {
            return "Job with such job title and description already exists";
        } else if(helpoJobRepository.existsByJobTitle(helpoJobDTO.getJobTitle()))
        {
            return "Job with such job title already exists";
        } else if(helpoJobRepository.existsByJobDescription(helpoJobDTO.getJobDescription()))
        {
            return "Job with such job description already exists";
        } else
        {
            ModelMapper modelMapper = new ModelMapper();
            HelpoJob helpoJob = modelMapper.map(helpoJobDTO, HelpoJob.class);
            helpoJobRepository.save(helpoJob);

            return "Job created successfully";
        }
    }

    public List<HelpoJob> getAllHelpoJobs()
    {
        return helpoJobRepository.findAll();
    }

    public List<HelpoJob> getHelpoJobsByOwnerID(Integer jobOwnerID)
    {
        return helpoJobRepository.findByJobOwnerID(jobOwnerID);
    }

    public HelpoJob getHelpoJobByHelpoJobTitle(String helpoJobTitle)
    {
        return helpoJobRepository.findByJobTitle(helpoJobTitle);
    }

    public String updateHelpoJobTitle(String helpoJobTitle) {
        if (!helpoJobRepository.existsByJobTitle(helpoJobTitle)) {
            return "Job you are trying to update does not exist";
        } else {
            HelpoJob helpoJob = helpoJobRepository.findByJobTitle(helpoJobTitle);
            helpoJob.setJobTitle(helpoJobTitle);

            helpoJobRepository.save(helpoJob);
            return "Job updated successfully";
        }
    }

    public String deleteHelpoJob(HelpoJobDTO helpoJobDTO)
    {
        if(!helpoJobRepository.existsByHelpoJobID(helpoJobDTO.getHelpoJobID()))
        {
            return "Job you are trying to delete does not exist";
        } else
        {
            System.out.println("VA CIA VIA" + helpoJobDTO.getHelpoJobID());
            helpoJobRepository.deleteByHelpoJobID(helpoJobDTO.getHelpoJobID());
            return "Job deleted successfully";
        }
    }

    public String deleteHelpoJobByHelpoJobTitleAndHelpoJobOwnerId(String helpoJobTitle, Integer helpoJobOwnerID)
    {
        if(!helpoJobRepository.existsByJobTitleAndJobOwnerID(helpoJobTitle,helpoJobOwnerID))
        {
            return "Job you are trying to delete does not exist";
        } else
        {
            helpoJobRepository.deleteByJobTitleAndJobOwnerID(helpoJobTitle, helpoJobOwnerID);
            return "Job deleted successfully";
        }
    }

}
