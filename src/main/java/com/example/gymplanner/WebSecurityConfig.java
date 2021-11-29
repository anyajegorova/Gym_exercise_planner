package com.example.gymplanner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.gymplanner.web.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		((HttpSecurity) http
		.authorizeRequests().antMatchers("/css/**").permitAll()
		.and()
		.authorizeRequests().antMatchers("/signup","/saveuser").permitAll()
		.and()
		.authorizeRequests()
			.anyRequest().authenticated()
			.and()
	.formLogin()
	.loginPage("/login")
		.defaultSuccessUrl("/exercises", true)
		.permitAll()
		.and()
	.oauth2Login()
		.loginPage("/login")
		.defaultSuccessUrl("/exercises", true)
		.userInfoEndpoint()
		.userService(oAuth2UserService)
	.and()
	.and())
	.logout()
		.permitAll();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		List<UserDetails> users = new ArrayList();
		
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		UserDetails user = User
				.withUsername("user")
				.password(passwordEncoder.encode("user"))
				.roles("USER")
				.build();
		users.add(user);
		
		user = User
				.withUsername("admin")
				.password(passwordEncoder.encode("admin"))
				.roles("ADMIN", "USER")
				.build();
		
		users.add(user);
		
		return new InMemoryUserDetailsManager(users);
		
	}
	@Autowired
	private OAuth2UserService oAuth2UserService;
	

}