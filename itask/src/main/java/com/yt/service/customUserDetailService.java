package com.yt.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class customUserDetailService implements UserDetailsService{

  
  
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		if(userName.equals("Mangesh"))
		{
			return new User("Mangesh", "Mangesh1234", new ArrayList<>());
		}else
			
		{
			throw new UsernameNotFoundException("User Not Found");
		}
		
		
		
	}

}
