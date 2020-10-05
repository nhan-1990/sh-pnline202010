package com.example.shoponline1.service;

import com.example.shoponline1.dto.ReviewDto;
import java.util.List;



public interface IReviewDtoService {
	
	List<ReviewDto> findAllReviewDto(int id);

}
