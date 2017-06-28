package com.websystique.springsecurity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springsecurity.model.User;
import com.websystique.springsecurity.repostory.UserRepository;

@Transactional
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	public User findById(int id) {
		return userRepository.findOne(id);
	}

	public Optional<User> findBySso(String ssoId) {
		return userRepository.findFirstBySsoId(ssoId);
	}

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findFirstBySsoId(ssoId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            System.out.println("User : " + user);
            return user;
        }else{
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
    }
}
