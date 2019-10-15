package com.infobae.ibproducto.reports.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infobae.ibproducto.reports.model.AuthUserDb;

@Repository
public interface AuthUserDao extends JpaRepository<AuthUserDb, Long>{

	Optional<AuthUserDb> findByUsername(String username);
	
}
