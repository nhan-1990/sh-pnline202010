package com.example.shoponline1.service;



import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;



public interface ImageService {
	
        byte[] uploadByteImage(MultipartFile file);
}
