package com.yt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;

import com.yt.service.customUserDetailService;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
  
	@Autowired
	private customUserDetailService customUserDetailsService;
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
		    .csrf()
		    .disable()
		    .cors()
		    .disable()
		    .authorizeRequests()
		    .antMatchers("/token").permitAll()
		    .and()
		    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		    .and()
		    .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
		    
		http.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(customUserDetailsService);
	}
	 @Bean
	  public PasswordEncoder passwordEncoder()
	  {
		  return NoOpPasswordEncoder.getInstance();
	  }
	 
	 @Bean
	 public AuthenticationManager authenticationManagerBean() throws Exception 
	 {
		return super.authenticationManagerBean();
		 
	 }
}
