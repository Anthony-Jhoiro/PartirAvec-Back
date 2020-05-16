package com.partiravec.uploadservice.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

@RestController
@CrossOrigin()
public class UploadController {
    private Environment environment;

    @Autowired
    public UploadController(Environment environment) {
        this.environment = environment;
    }

    @PostMapping("/")
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload";
        }

        try {
            byte[] bytes = file.getBytes();
            String UPLOADED_FLODER = environment.getProperty("app.imagepath");
            String EXPOSED_PATH = environment.getProperty("app.imageExposePath");

            String image_name = String.valueOf(Calendar.getInstance().getTimeInMillis() + ((int)(Math.random()*1000)));
            Path path = Paths.get(UPLOADED_FLODER + image_name);
            Files.write(path, bytes);
            return EXPOSED_PATH+image_name;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "An error occured";
    }

    @GetMapping(value = "/{url}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable("url") String url) throws IOException {
        String UPLOADED_FLODER = environment.getProperty("app.imagepath");
        InputStream in = new FileInputStream(UPLOADED_FLODER + url);
        return IOUtils.toByteArray(in);
    }

}
