package com.infomover.poc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infomover.poc.entity.UserEntity;

@Repository
public interface UserRespository extends CrudRepository<UserEntity, String>{

}
