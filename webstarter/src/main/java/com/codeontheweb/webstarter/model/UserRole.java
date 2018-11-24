package com.codeontheweb.webstarter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_roles")
public class UserRole
{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private AppUser user;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	public AppUser getUser()
	{
		return user;
	}

	public void setUser(AppUser newUser)
	{
		user = newUser;
	}

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role newRole)
	{
		role = newRole;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long newId)
	{
		id = newId;
	}
}
