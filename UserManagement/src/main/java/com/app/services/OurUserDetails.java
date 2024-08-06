package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dao.UserDao;

@Service
public class OurUserDetails implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		return userDao.findByEmail(username).orElseThrow() ;
	}

}
