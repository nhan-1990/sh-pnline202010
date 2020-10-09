package com.example.shoponline1.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ChangePassForm {
	private int id;
	private String email;
	private String oldPass;
	private String newPass;
	private String comfirmPass;
}
