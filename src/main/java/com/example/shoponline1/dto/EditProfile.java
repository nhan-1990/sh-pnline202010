package com.example.shoponline1.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EditProfile {
	private int id;
	private String email;
	private String name;
	private String address;
	private Long phone;
	private String emails;
}
