package com.example.shoponline1.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Component
@Data
public class ProductDetailAdminDto {
	
	private int productDetailId;
	private String amount;
	private String price;
	private int colorId;
	private int configId;
	private int productId;
	private String colorVsConfig;
	private String colorVsConfigs;        
	private byte[] image1;
        private byte[] image2;
        private MultipartFile file1;
        private MultipartFile file2;
	private String amouts;
	private String prices;

}
