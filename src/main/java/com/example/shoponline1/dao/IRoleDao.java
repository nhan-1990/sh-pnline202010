package com.example.shoponline1.dao;

import com.example.shoponline1.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRoleDao extends JpaRepository<Role, Integer>{
	Role findByRoleName(String name);

}
