package com.infomover.security.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infomover.security.poc.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	public List<Role> findRoleByName(String name);
}