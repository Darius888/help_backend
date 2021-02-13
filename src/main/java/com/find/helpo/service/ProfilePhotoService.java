package com.find.helpo.service;

import com.find.helpo.DTO.ProfilePhotoDTO;
import com.find.helpo.model.ProfilePhoto;
import com.find.helpo.repository.ProfilePhotoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class ProfilePhotoService extends FileService {

    @Autowired
    private ProfilePhotoRepository profilePhotoRepository;

    public String uploadNewProfilePhoto(ProfilePhotoDTO profilePhotoDTO, RedirectAttributes redirectAttributes)
    {
        if(profilePhotoRepository.existsByRelatedUserID(profilePhotoDTO.getRelatedUserID()))
        {
            return "This user already has a profile picture. Please delete current profile picture and then upload another one";
        } else
        {
            ModelMapper modelMapper = new ModelMapper();
            ProfilePhoto profilePhoto = modelMapper.map(profilePhotoDTO, ProfilePhoto.class);
            profilePhoto.setAbsolutePath("/storage" + profilePhotoDTO.getProfilePhoto().getOriginalFilename());
            profilePhoto.setImageName(profilePhotoDTO.getProfilePhoto().getOriginalFilename());
            profilePhotoRepository.save(profilePhoto);
//            uploadFile(profilePhotoDTO.getProfilePhoto(), redirectAttributes);
            return "File uploaded successfully";
        }
    }
}
