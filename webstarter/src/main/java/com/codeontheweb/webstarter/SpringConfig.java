package com.codeontheweb.webstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource({"classpath:application.properties", "classpath:database.properties"})
public class SpringConfig implements WebMvcConfigurer
{
	@Autowired
	private Environment env;

	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		//return new BCryptPasswordEncoder();
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource()
	{
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl(env.getProperty("spring.datasource.url"));
		driverManagerDataSource.setUsername(env.getProperty("spring.datasource.username"));
		driverManagerDataSource.setPassword(env.getProperty("spring.datasource.password"));
		return driverManagerDataSource;
	}
}
