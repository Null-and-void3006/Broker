package com.Tim401_6.Broker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Tim401_6.Broker.services.EndpointService;

import java.util.Map;

@RestController
public class EndpointController {

	@Autowired
	private EndpointService endpointService;

	@GetMapping("/{service}/{endpoint}")
	public String endpointRouting(@PathVariable(value="service") String service, @PathVariable(value="endpoint") String end, @RequestParam Map<String,String> allRequestParams) {
		return endpointService.route(service, end, allRequestParams);
	}

	@PostMapping("/{service}/{endpoint}")
	public String postEndpointRouting(@PathVariable(value="service") String service, @PathVariable(value="endpoint") String end, @RequestBody Map<String, Object> payload) {
		return endpointService.routeWithPayload(service, end, payload);
	}

	@PutMapping ("/{service}/{endpoint}")
	public String putEndpointRouting(@PathVariable(value="service") String service, @PathVariable(value="endpoint") String end, @RequestBody String payload) {
		return endpointService.routePutWithPayload(service, end, payload);
	}
}
