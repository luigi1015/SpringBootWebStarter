package com.codeontheweb.webstarter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
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
		//TODO: Put in logic to register the user.
		//TODO: Add in HTTPS
		logger.info("Username: {}", user.getUsername());
		model.addAttribute("errorMessage", "Registration logic hasn't been implemented yet");
		model.addAttribute("user", new AppUser());
		return "register";
	}
}
