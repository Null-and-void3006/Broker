package com.Tim401_6.Broker.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.Tim401_6.Broker.services.TransformService;

@RestController
public class TransformController {

	@Autowired
	private TransformService transformService;

	@PostMapping("/transform/{type}")
	public String postEndpointRouting(@RequestBody Map<String, Object> payload, @PathVariable(value="type") String type) {
		return transformService.routeWithPayload(payload, type);
	}

	@PutMapping ("/transform/{type}")
	public String putEndpointRouting(@RequestBody String payload, @PathVariable(value="type") String type) {
		return transformService.routePutWithPayload(payload, type);
	}
}
