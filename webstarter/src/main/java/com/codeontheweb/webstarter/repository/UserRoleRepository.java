package com.codeontheweb.webstarter.repository;

import org.springframework.data.repository.CrudRepository;

import com.codeontheweb.webstarter.model.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long>
{
	//A bunch of CRUD method are automatically created by Spring.
}
