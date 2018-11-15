package com.codeontheweb.webstarter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "User")
public class AppUser
{
	@Id
	@Column(name = "id")
	private String id;//TODO: Use UUIDs for the id.

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "email", unique = true)
	@Email(message = "Please provide a valid e-mail")
	private String email;

	@Column(name = "password")
	@Transient
	private String password;

	@Column(name = "enabled")
	private boolean enabled;

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
}
