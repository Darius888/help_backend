package com.find.helpo.controller;


import com.find.helpo.DTO.ProfilePhotoDTO;
import com.find.helpo.service.ProfilePhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@CrossOrigin
@RestController
@RequestMapping(path = "/profile")
public class HelpoUserProfileController {

    @Autowired
    private ProfilePhotoService fileService;

    @RequestMapping(value = "/uploadProfilePhoto",
                    method = RequestMethod.POST,
                    consumes = {"multipart/form-data"})
    public String singleFileUpload(@ModelAttribute ProfilePhotoDTO profilePhotoDTO,
                                   RedirectAttributes redirectAttributes) {

        return fileService.uploadNewProfilePhoto(profilePhotoDTO,redirectAttributes);
    }

    @RequestMapping(value = "/getFile/{filename}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        return fileService.serveFile(filename);
    }

    @RequestMapping(value = "/storage/{imagename}", method = RequestMethod.GET)
    @ResponseBody
    public String getUrl(@PathVariable String imagename) throws MalformedURLException {
        return "http://localhost:8080/profile/storage/1.jpeg";
    }


}
