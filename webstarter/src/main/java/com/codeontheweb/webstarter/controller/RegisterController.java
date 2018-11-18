package com.codeontheweb.webstarter.controller;

import java.util.UUID;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codeontheweb.webstarter.model.AppUser;
import com.codeontheweb.webstarter.service.UserService;

@Controller
public class RegisterController
{
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserService userService;
	private Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	public RegisterController( BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService )
	{
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userService = userService;
	}

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
		logger.info("Username: {}", user.getUsername());
		String password = user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setEnabled(true);
		user.setId(UUID.randomUUID().toString());
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
