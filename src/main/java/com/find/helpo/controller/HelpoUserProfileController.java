package com.find.helpo.controller;


import com.find.helpo.service.ProfilePhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/profile")
public class HelpoUserProfileController {

    @Autowired
    private ProfilePhotoService fileService;

    @RequestMapping(value = "/uploadProfilePhoto", method = RequestMethod.POST)
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        return fileService.uploadFile(file,redirectAttributes);
    }

    @RequestMapping(value = "/getFile/{filename}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        return fileService.serveFile(filename);
    }

}
