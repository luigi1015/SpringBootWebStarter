package com.codeontheweb.webstarter.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codeontheweb.webstarter.model.AppUser;
import com.codeontheweb.webstarter.model.Role;
import com.codeontheweb.webstarter.model.UserRole;
import com.codeontheweb.webstarter.repository.RoleRepository;
import com.codeontheweb.webstarter.repository.UserRoleRepository;
import com.codeontheweb.webstarter.service.UserService;

@Controller
public class RegisterController
{
	@Autowired
	private PasswordEncoder passwordEncoder;
	//private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	private Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@GetMapping("/register")
	public String showRegistrationForm( Model model )
	{
		model.addAttribute("user", new AppUser());
		return "register";
	}

	@PostMapping("/register")
	public String processRegistrationForm( @ModelAttribute AppUser user, Model model )
	{
		//TODO: Add in HTTPS
		//TODO: Put it some more security
		//Get the USER role.
		Optional<Role> findUserRole = roleRepository.findByName("USER");
		UserRole userUserRole = new UserRole();
		//TODO: Have the app set the roles up automatically on startup.
		if( findUserRole.isPresent() )
		{
			Role userRole = findUserRole.get();
			userUserRole.setRole(userRole);
		}
		else
		{
			Role userRole = new Role();
			userRole.setName("USER");
			roleRepository.save(userRole);
			userUserRole.setRole(userRole);
		}
		String password = user.getPassword();
		user.setPassword(passwordEncoder.encode(password));
		//user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setEnabled(true);
		user.setId(UUID.randomUUID().toString());
		Set<UserRole> roleSet = new HashSet<>();
		roleSet.add(userUserRole);
		user.setRoles(roleSet);
		userUserRole.setUser(user);
		try
		{
			userService.saveUser(user);
		}
		catch( TransactionSystemException | DataIntegrityViolationException ex )
		{
			//TODO: Put in a better error.
			model.addAttribute("errorMessage", "Error registering the user, please verify that you entered in valid information.");
			user.setPassword(password);//Set the password back to what the user entered.
			model.addAttribute("user", user);
			return "register";
		}
		model.addAttribute("regularMessage", "User has been registered.");
		return "redirect:/home";
	}
}
