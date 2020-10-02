package com.Tim401_6.Broker.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class UpdateServiceService {
	
	@RequestMapping(value = "/update-service", method = RequestMethod.POST)
	public String test()
	{
		return "This is no the best class in the world, it is just a tribute";
	}

}
