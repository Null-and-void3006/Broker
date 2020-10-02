package com.Tim401_6.Broker.services;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Tim401_6.Broker.model.Endpoint;
import com.Tim401_6.Broker.model.services.DeleteServiceRow;
import com.Tim401_6.Broker.repository.AuthorizationGroupRepository;
import com.Tim401_6.Broker.repository.EndpointRepository;
import com.Tim401_6.Broker.repository.ServiceRepository;
import com.Tim401_6.Broker.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DeleteServiceService {
	
	//All the needed repositories
	@Autowired
	EndpointRepository endpointRepository;
	@Autowired
	AuthorizationGroupRepository authorizationGroupRepository;
	@Autowired
	ServiceRepository serviceRepository;
	@Autowired
	UserRepository userRepository;
	
	
	
	
	@PutMapping(path = "/delete-service")
    public String addTableRow(@RequestBody DeleteServiceRow deleteService) throws IOException {

		
		//Cant delete a service if you are not a provider!
		if (!userRepository.findById(deleteService.getUserId()).get().getType().equals("PROVIDER"))
			return "You are not a provider, you cannot delete services!";
		
		if (serviceRepository.findFirstByServiceName(deleteService.getServiceName()).get(0).getUserId() != deleteService.getUserId())
			return "You are not the owner of this service!";
		
		//We need to delete the row in the services table
		com.Tim401_6.Broker.model.Service s = (com.Tim401_6.Broker.model.Service) serviceRepository.findFirstByServiceName(deleteService.getServiceName());
		serviceRepository.deleteById(s.getId());
		
		//Now, we need to delete every row in 
		//endpoints table that has the same service_id as our deleted service
		
		
		ArrayList<Endpoint> all = (ArrayList<Endpoint>) endpointRepository.findAll();
		for (Endpoint e : all)
		{
			int id = e.getId();
			int service_id = e.getServiceId();
			
			if (id == service_id) {
				endpointRepository.deleteById(id);
			}
		}
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(s);
    }
}
