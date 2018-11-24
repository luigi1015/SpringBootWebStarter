package com.codeontheweb.webstarter.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "users")
public class AppUser
{
	@Id
	@Column(name = "id")
	private String id;//TODO: Use UUIDs for the id.

	@NotNull
	@Column(name = "username", unique = true)
	private String username;

	@NotNull
	@Column(name = "email", unique = true)
	@Email(message = "Please provide a valid e-mail")
	private String email;

	@NotNull
	@Column(name = "password")
	@Transient
	private String password;

	@NotNull
	@Column(name = "enabled")
	private boolean enabled;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<UserRole> userRoles;

	public String getId()
	{
		return id;
	}

	public void setId(String newId)
	{
		id = newId;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String newUsername)
	{
		username = newUsername;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String newEmail)
	{
		email = newEmail;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String newPassword)
	{
		password = newPassword;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(boolean isEnabled)
	{
		enabled = isEnabled;
	}

	public Set<UserRole> getRoles()
	{
		return userRoles;
	}

	public void setRoles( Set<UserRole> newRoles )
	{
		userRoles = newRoles;
	}
}
