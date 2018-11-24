package com.codeontheweb.webstarter.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
public class Role
{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@NotNull
	@Column(name = "role_name", nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<UserRole> userRoles;

	public String getName()
	{
		return name;
	}

	public void setName(String newName)
	{
		name = newName;
	}

	public Set<UserRole> getUserRoles()
	{
		return userRoles;
	}

	public void setUserRoles( Set<UserRole> newRoles )
	{
		userRoles = newRoles;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int newId)
	{
		id = newId;
	}
}
