package com.find.helpo.service;

import com.find.helpo.DTO.HelpoJobPhotoDTO;
import com.find.helpo.model.HelpoJob;
import com.find.helpo.model.HelpoJobPhoto;
import com.find.helpo.model.HelpoUser;
import com.find.helpo.repository.HelpoJobPhotoRepository;
import com.find.helpo.repository.HelpoJobRepository;
import com.find.helpo.repository.HelpoUserRepository;
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

    @Autowired
    private HelpoJobRepository helpoJobRepository;

    @Autowired
    private HelpoUserRepository helpoUserRepository;

    public String uploadNewHelpoJobPhotos(MultipartFile[] files, String email, Integer helpoJobID, RedirectAttributes redirectAttributes)
    {
        System.out.println("AAAA" + helpoUserRepository.findByEmail(email).toString());

        HelpoUser user = helpoUserRepository.findByEmail(email);

        Integer countOfJobPhotos = helpoJobPhotoRepository.countByRelatedJobID(1);
        int countOfJobPhotosWithSentPhotos = countOfJobPhotos + files.length;

        if(countOfJobPhotos == 7)
        {
            return "User already has already uploaded 7 images, which is the maximum amount";
        }

        if(files.length > 7)
        {
            return "User is trying to upload more than 7 helpo job photos which is the maximum amount";
        }

        if(countOfJobPhotosWithSentPhotos > 7)
        {
            return "User already has some images uploaded and with the ones currently being added it adds up to amount which is bigger then 7";
        }

        for (MultipartFile file : files) {
            if (helpoJobPhotoRepository.existsByRelatedJobID(user.getHelpoUserID()) && helpoJobPhotoRepository.existsByImageName(file.getOriginalFilename())) {
                return "Helpo job photo with title " + file.getOriginalFilename() + " already exists for this user";
            } else {
                HelpoJobPhoto helpoJobPhoto = new HelpoJobPhoto();
                helpoJobPhoto.setAbsolutePath("/storage/" + helpoJobID + "-" + user.getHelpoUserID() + file.getOriginalFilename());
                helpoJobPhoto.setImageName(helpoJobID + "-" + user.getHelpoUserID() + "-" + "helpoJob" + "-" + file.getOriginalFilename());
                helpoJobPhoto.setRelatedJobID(helpoJobID);
                helpoJobPhotoRepository.save(helpoJobPhoto);
                uploadFile(file, helpoJobPhoto.getImageName(), redirectAttributes);
            }
        }
        return "Files uploaded successfully";
    }

}
