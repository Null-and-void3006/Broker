package com.Tim401_6.Broker.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Tim401_6.Broker.model.services.AddEndpointRow;
import com.Tim401_6.Broker.repository.AuthorizationGroupRepository;
import com.Tim401_6.Broker.repository.EndpointRepository;
import com.Tim401_6.Broker.repository.ServiceRepository;
import com.Tim401_6.Broker.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RegisterEndpointService {

	//All the needed repositories
	@Autowired
	EndpointRepository endpointRepository;
	@Autowired
	AuthorizationGroupRepository authorizationGroupRepository;
	@Autowired
	ServiceRepository serviceRepository;
	@Autowired
	UserRepository userRepository;
	
	@PutMapping(path = "/register-endpoint")
    public String addTableRow(@RequestBody AddEndpointRow addEndpoint) throws IOException {

		String endpoint_name = addEndpoint.getEndpointName();
		String path = addEndpoint.getPath();
		int service_id = addEndpoint.getServiceId();
		int method_id = addEndpoint.getMethodId();
		String request_params = addEndpoint.getRequestParams();
		String response_params = addEndpoint.getResponseParams();
	    
	    
	    
	    if (!userRepository.findById(addEndpoint.getUserId()).get().getType().equals("PROVIDER"))
			return "You are not a provider, you cannot register services!";
	    
	    //We need to check if the given service exists!
	    if (!serviceRepository.existsById(service_id))
	  	    	return "Nonexistant service! Check your data.";  
	    
	    //Creating an endpoint
		com.Tim401_6.Broker.model.Endpoint e = new com.Tim401_6.Broker.model.Endpoint(endpoint_name, path, service_id, method_id, request_params, response_params, false);
		
		//First we need to add the endpoint to the database
		endpointRepository.save(e);
		
		
		//Then we get its ID (if we do it before, the ID is 0 by default and that violates database constraints!
		int endpoint_id = endpointRepository.findFirstByEndpointName(endpoint_name).get(0).getId();

		
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString("Added the needed rows");
    }
}
