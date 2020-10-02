package com.Tim401_6.Broker.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.Tim401_6.Broker.enums.AttributeType;
import com.Tim401_6.Broker.model.services.AddServiceRow;
import com.Tim401_6.Broker.repository.ServiceRepository;
import com.Tim401_6.Broker.repository.UserRepository;
import com.Tim401_6.Broker.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RegisterServiceService {
	
	@Autowired
	ServiceRepository serviceRepository;
	@Autowired
	UserRepository userRepository;

	@PutMapping(path = "/register-service")
    public String addTableRow(@RequestBody AddServiceRow addService) throws IOException {

		if (!userRepository.findById(addService.getUserId()).get().getType().equals("PROVIDER"))
			return "You are not a provider, you cannot register services!";


		com.Tim401_6.Broker.model.Service s = 
				new com.Tim401_6.Broker.model.Service(addService.getServiceName(), addService.getPath(), addService.getUserId(), addService.isAbstract(), null);



		serviceRepository.save(s);
		System.out.println(s.getId());

		List<AddServiceRow> servisi = addService.getChildren();
		if(servisi != null){
			for(int i = 0 ; i < servisi.size() ; i++){
				com.Tim401_6.Broker.model.Service ser =
						new com.Tim401_6.Broker.model.Service(servisi.get(i).getServiceName(), servisi.get(i).getPath(), servisi.get(i).getUserId(), servisi.get(i).isAbstract(), s.getId());
				serviceRepository.save(ser);
			}
		}
		
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(s);
    }

}
