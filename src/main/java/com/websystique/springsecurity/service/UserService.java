package com.websystique.springsecurity.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.websystique.springsecurity.model.User;

public interface UserService extends UserDetailsService {

	User findById(int id);
	
	Optional<User> findBySso(String sso);
	
}