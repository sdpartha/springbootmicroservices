package com.infomover.security.poc.service;

import com.infomover.security.poc.entity.User;

public interface UserService {

	void save(User user);

	User findByUsername(String username);

}
