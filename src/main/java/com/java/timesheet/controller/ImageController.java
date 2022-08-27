package com.java.timesheet.controller;


import com.java.timesheet.model.Image;
import com.java.timesheet.model.User;
import com.java.timesheet.repository.ImageRepository;
import com.java.timesheet.repository.UserRepository;
import com.java.timesheet.util.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:8082") open for specific port
@CrossOrigin() // open for all ports
public class ImageController {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/upload/image/{email}")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@PathVariable("email") String email, @RequestParam("image") MultipartFile file)
            throws IOException {
        User user = userRepository.findByEmail(email);
        Image image=Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes())).build();
        user.setImage(image);
        userRepository.save(user);
        imageRepository.save(image);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " + file.getOriginalFilename()));
    }

    @GetMapping(path = {"/get/image/info/{name}"})
    public Image getImageDetails(@PathVariable("name") String name) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return Image.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
    }

    @GetMapping(path = {"/get/image/{email}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String email) throws IOException {
        User user = userRepository.findByEmail(email);
        Image dbImage = user.getImage();

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.getType()))
                .body(ImageUtility.decompressImage(dbImage.getImage()));
    }
}