package com.example.shoponline1.dao;

import com.example.shoponline1.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IReviewDao extends JpaRepository<Review, Integer>{

}
