package com.sky.getYourWayBack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sky.getYourWayBack.data.entity.User;
import com.sky.getYourWayBack.data.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User currentUser = repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core
		.userdetails.User(username, currentUser.getPassword()
		, true, true, true, true,
		AuthorityUtils.createAuthorityList(currentUser.getRole()));
		return user;
	}

}
