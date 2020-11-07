package com.example.demo.security.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.security.dao.AuthenticationDao;



@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AuthenticationDao authenticationDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		if (authenticationDao.findByUsername(username).getUserName().equals(username))
		{
			return new User(authenticationDao.findByUsername(username).getUserName(), authenticationDao.findByUsername(username).getPassword(),
							new ArrayList<>());
		} 
		else
		{
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}