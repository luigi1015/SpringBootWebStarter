package com.codeontheweb.webstarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeontheweb.webstarter.model.AppUser;
import com.codeontheweb.webstarter.repository.UserRepository;

@Service
public class UserService
{
	@Autowired
	private UserRepository userRepository;
	
	public AppUser findByEmail( String email )
	{
		return userRepository.findByEmail(email);
	}
	
	public AppUser findByUsername( String username )
	{
		return userRepository.findByUsername(username);
	}
	
	public void saveUser( AppUser user )
	{
		userRepository.save(user);
	}
}
