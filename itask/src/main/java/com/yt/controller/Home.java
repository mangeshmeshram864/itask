package com.yt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
	 
	@RequestMapping("/welcome")
	public String Welcome()
	{
		String text="this is prvate paeg ";
		
		return text;
	}
	

	

}
