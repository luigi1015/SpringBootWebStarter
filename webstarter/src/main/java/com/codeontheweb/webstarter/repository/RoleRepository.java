package com.codeontheweb.webstarter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.codeontheweb.webstarter.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer>
{
	//A bunch of CRUD method are automatically created by Spring.
	Optional<Role> findByName( String name );

	@Query("SELECT count(*)>0 FROM Role WHERE role_name=?1")
	boolean roleExistsByName( String name );
}
