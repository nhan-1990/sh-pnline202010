package com.example.shoponline1.dao;



import com.example.shoponline1.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IImagesDao extends JpaRepository<Images, Integer>{



}
