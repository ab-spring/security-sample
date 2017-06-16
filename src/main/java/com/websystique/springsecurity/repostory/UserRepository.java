/*
 * www.qwfys.org Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.websystique.springsecurity.repostory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websystique.springsecurity.model.User;

/**
 * 
 * 
 * @author lwk
 * @version $Id: UserRepository.java, v 0.1 2017-06-16 15:15:41 lwk Exp$
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findFirstBySsoId(String ssoId);

}
