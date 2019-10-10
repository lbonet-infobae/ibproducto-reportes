package com.infobae.ibproducto.reports.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infobae.ibproducto.reports.model.UserDb;

@Repository
public interface UserDao extends JpaRepository<UserDb, Long>{

	Optional<UserDb> findByName(String name); 
	
}
