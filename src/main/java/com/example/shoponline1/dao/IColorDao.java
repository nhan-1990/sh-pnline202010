package com.example.shoponline1.dao;

import com.example.shoponline1.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IColorDao extends JpaRepository<Color, Integer>{
	
}
