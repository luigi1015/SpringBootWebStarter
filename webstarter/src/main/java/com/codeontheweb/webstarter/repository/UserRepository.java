package com.codeontheweb.webstarter.repository;

import org.springframework.data.repository.CrudRepository;

import com.codeontheweb.webstarter.model.AppUser;

public interface UserRepository extends CrudRepository<AppUser, String>
{
	AppUser findByEmail(String email);
	AppUser findByUsername(String username);
}
