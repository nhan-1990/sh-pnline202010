package com.example.shoponline1.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public byte[] uploadByteImage(MultipartFile file) {

        byte[] image = null;
        try {
            image = file.getBytes();
        } catch (IOException e) {
        }
        return image;
    }
}
