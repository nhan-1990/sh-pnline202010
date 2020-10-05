package com.example.shoponline1.dto;

import lombok.Data;

@Data
public class ProductAdminDto {
	private int id;
	private String name;
	private String promotion;
	private String trademark;
	private int promotionId;
	private int trademarkId;
	private String note;
}
