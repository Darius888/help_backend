package com.find.helpo.service;


import com.find.helpo.DTO.HelpoJobDTO;
import com.find.helpo.model.HelpoJob;
import com.find.helpo.model.HelpoJobGet;
import com.find.helpo.model.HelpoJobPhoto;
import com.find.helpo.model.HelpoUser;
import com.find.helpo.repository.HelpoJobPhotoRepository;
import com.find.helpo.repository.HelpoJobRepository;
import com.find.helpo.repository.HelpoUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class HelpoJobService {

    @Autowired
    private HelpoJobRepository helpoJobRepository;

    @Autowired
    private HelpoUserRepository helpoUserRepository;

    @Autowired
    private HelpoJobPhotoService helpoJobPhotoService;

    @Autowired
    private HelpoJobPhotoRepository helpoJobPhotoRepository;

    public String createNewJob(MultipartFile[] jobPhotos, HelpoJobDTO helpoJobDTO, String userEmail, RedirectAttributes redirectAttributes)
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

            helpoJob.setJobOwnerID(helpoUserRepository.findByEmail(userEmail).getHelpoUserID());
            helpoJob.setJobFavoredStatus("false");
            helpoJob.setJobFavoredByUserId(null);

            helpoJobRepository.save(helpoJob);

            //ADD TO IMAGE NAME EITHER ITS JOB PHOTO
            helpoJobPhotoService.uploadNewHelpoJobPhotos(jobPhotos, userEmail, helpoJob.getHelpoJobID(), redirectAttributes);


            return "Job created successfully";
        }
    }

    public List<HelpoJobGet> getAllHelpoJobs()
    {
        String imagePath = "http://localhost:8080/storage/";

        List<HelpoJobGet> helpoJobGets = new ArrayList<>();

        List<HelpoJob> helpoJobs = helpoJobRepository.findAll();


        for(HelpoJob helpoJob: helpoJobs)
        {
            HelpoJobGet helpoJobGet = new HelpoJobGet();


            helpoJobGet.setJobTitle(helpoJob.getJobTitle());
            helpoJobGet.setJobPostDate(helpoJob.getJobPostDate());
            helpoJobGet.setJobType(helpoJob.getJobType());
            helpoJobGet.setJobDescription(helpoJob.getJobDescription());
            helpoJobGet.setJobReward(helpoJob.getJobReward());
            helpoJobGet.setJobStatus(helpoJob.getJobStatus());
            helpoJobGet.setJobFavoredStatus(helpoJob.getJobFavoredStatus());

            HelpoUser helpoUser = helpoUserRepository.findByHelpoUserID(helpoJob.getJobOwnerID());

            helpoJobGet.setJobOwnerFirstName(helpoUser.getFirstName());
            helpoJobGet.setJobOwnerLastName(helpoUser.getLastName());

            List<HelpoJobPhoto> relatedHelpoJobPhotos = helpoJobPhotoRepository.findAllByRelatedJobID(helpoJob.getHelpoJobID());

            ArrayList<String> helpoJobPhotoPaths = new ArrayList<>();

            System.out.println(relatedHelpoJobPhotos.toString());

            for(HelpoJobPhoto helpoJobPhoto: relatedHelpoJobPhotos)
            {
                helpoJobPhotoPaths.add(imagePath + helpoJobPhoto.getImageName());
            }

            helpoJobGet.setJobPhotoList(helpoJobPhotoPaths);

            helpoJobGets.add(helpoJobGet);


        }


        return helpoJobGets;
    }

    public List<HelpoJob> getHelpoJobsByOwnerID(Integer jobOwnerID)
    {
        return helpoJobRepository.findByJobOwnerID(jobOwnerID);
    }

    public HelpoJob getHelpoJobByHelpoJobTitle(String helpoJobTitle)
    {
        return helpoJobRepository.findByJobTitle(helpoJobTitle);
    }

    public String updateHelpoJob(HelpoJobDTO helpoJobDTO) {
        if (!helpoJobRepository.existsByHelpoJobID(helpoJobDTO.getHelpoJobID())) {
            return "Job you are trying to update does not exist";
        } else {
            HelpoJob helpoJob = helpoJobRepository.findByHelpoJobID(helpoJobDTO.getHelpoJobID());
            helpoJob.setJobTitle(helpoJobDTO.getJobTitle());
            helpoJob.setJobPostDate(helpoJobDTO.getJobPostDate());
            helpoJob.setJobType(helpoJobDTO.getJobType());
            helpoJob.setJobDescription(helpoJobDTO.getJobDescription());
            helpoJob.setJobReward(helpoJobDTO.getJobReward());
            helpoJob.setJobStatus(helpoJobDTO.getJobStatus());
            helpoJob.setJobOwnerID(1);
//            helpoJob.setJobFavoredStatus(helpoJobDTO.getJobFavoredStatus());
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
