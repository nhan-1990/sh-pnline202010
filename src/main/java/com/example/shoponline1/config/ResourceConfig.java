package com.example.shoponline1.config;

import java.io.File;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {

	 @Override
     public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		 
         registry.addResourceHandler("/uploads/**").addResourceLocations("file:uploads/");
     }
}
