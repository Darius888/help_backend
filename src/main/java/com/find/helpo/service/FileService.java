package com.find.helpo.service;

import com.find.helpo.exception.FileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileService {

    private static final String UPLOADED_FOLDER = "/home/darius/Idejos/Helpo/Backend/helpo/storage/";

    public String uploadFile(MultipartFile file, RedirectAttributes redirectAttributes)
    {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }


    public String uploadMultipleFiles(ArrayList<MultipartFile> files, RedirectAttributes redirectAttributes)
    {
        if(files.isEmpty())
        {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        } else
        {
            for(MultipartFile file: files)
            {
                try {
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                    Files.write(path, bytes);

                    redirectAttributes.addFlashAttribute("message",
                            "You successfully uploaded '" + file.getOriginalFilename() + "'");
                    // Get the file and save it somewhere

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return "redirect:/uploadStatus";
        }
    }

    public ResponseEntity<Resource> serveFile(String fileName)
    {
        Resource file = loadFileAsResource(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = Path.of(UPLOADED_FOLDER + fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
    }
}
