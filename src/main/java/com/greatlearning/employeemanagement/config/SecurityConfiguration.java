package com.greatlearning.employeemanagement.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
		.withUser(User.withUsername("user1").password(getPasswordEncoder().encode("welcome1")).roles("ADMIN"))
		.withUser(User.withUsername("user2").password(getPasswordEncoder().encode("welcome2")).roles("USER"));
	}

	
	  @Override public void configure(WebSecurity web) throws Exception {
	  web.ignoring().antMatchers("/h2-console/**"); }
	 
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/addEmployee").hasAuthority("ROLE_ADMIN")
		.antMatchers("/","/getAllEmployees", "/getEmployeeById",
				"/getEmployeesByName", "/getEmployeesSortedByName",
				"/deletebyID", "/updateEmployee").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginProcessingUrl("/login").permitAll()
		.and()
		.logout().logoutSuccessUrl("/login").permitAll()
		.and()
		.cors().and().csrf().disable();
	}
	
	

}
