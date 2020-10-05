package com.example.shoponline1.dao;

import com.example.shoponline1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User, Integer>{
	User findByEmail(String email);
}
