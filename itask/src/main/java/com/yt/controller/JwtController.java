package com.yt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yt.helper.JwtUtil;
import com.yt.model.JwtRequest;
import com.yt.service.customUserDetailService;

@RestController
public class JwtController {

	@Autowired
	private customUserDetailService customUserDetailsService ;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager ;
	
	@PostMapping("/token")
	public ResponseEntity<?> generteToken(@RequestBody JwtRequest jwtRequest) throws Exception 
	{
		System.out.println(jwtRequest);
		
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
			
		}catch(UsernameNotFoundException e)
		{
			e.printStackTrace();
			throw new Exception("Bad credentials ");
		}
		catch(BadCredentialsException e)
		{
			e.printStackTrace();
			throw new Exception("Bad credentials ");
		}
		//fine area..
		
		UserDetails userByUsername = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
	     
		String token=this.jwtUtil.generateToken(userByUsername);
		System.out.println("JWT "+token);
		return ResponseEntity.ok(token);
		//
	}
	
	
}
