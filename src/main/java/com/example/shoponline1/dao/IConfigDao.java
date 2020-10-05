package com.example.shoponline1.dao;

import com.example.shoponline1.entity.Configurator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IConfigDao extends JpaRepository<Configurator, Integer>{

}
