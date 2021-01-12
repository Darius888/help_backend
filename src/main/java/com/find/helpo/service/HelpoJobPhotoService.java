package com.find.helpo.service;

import com.find.helpo.DTO.HelpoJobPhotoDTO;
import com.find.helpo.model.HelpoJobPhoto;
import com.find.helpo.repository.HelpoJobPhotoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Service
public class HelpoJobPhotoService extends FileService {

    @Autowired
    private HelpoJobPhotoRepository helpoJobPhotoRepository;

    public String uploadNewHelpoJobPhotos(ArrayList<MultipartFile> files, ArrayList<HelpoJobPhotoDTO> helpoJobPhotoDTOS, RedirectAttributes redirectAttributes)
    {
        ModelMapper modelMapper = new ModelMapper();
        Integer countOfJobPhotos = helpoJobPhotoRepository.countByRelatedJobID(helpoJobPhotoDTOS.get(0).getRelatedJobID());
        int countOfJobPhotosWithSentPhotos = countOfJobPhotos + files.size();

        if(countOfJobPhotos == 7)
        {
            return "User already has already uploaded 7 images, which is the maximum amount";
        }

        if(files.size() > 7)
        {
            return "User is trying to upload more than 7 helpo job photos which is the maximum amount";
        }

        if(countOfJobPhotosWithSentPhotos > 7)
        {
            return "User already has some images uploaded and with the ones currently being added it adds up to amount which is bigger then 7";
        }

        if(files.size() != helpoJobPhotoDTOS.size())
        {
            return "All images has to have an id past on to it";
        }

        for(int i=0; i<helpoJobPhotoDTOS.size(); i++)
        {
            if(helpoJobPhotoRepository.existsByRelatedJobID(helpoJobPhotoDTOS.get(i).getRelatedJobID()) && helpoJobPhotoRepository.existsByImageName(files.get(i).getOriginalFilename()))
            {
               return  "Helpo job photo with title " + files.get(i).getOriginalFilename() + " already exists for this user";
            } else
            {
                HelpoJobPhoto helpoJobPhoto = modelMapper.map(helpoJobPhotoDTOS.get(i), HelpoJobPhoto.class);
                helpoJobPhoto.setAbsolutePath("/storage/" + files.get(i).getOriginalFilename());
                helpoJobPhoto.setImageName(files.get(i).getOriginalFilename());
                System.out.println("ABC" + helpoJobPhoto.toString());
                helpoJobPhotoRepository.save(helpoJobPhoto);
                uploadFile(files.get(i), redirectAttributes);
            }
        }
        return "Files uploaded successfully";
    }

}
