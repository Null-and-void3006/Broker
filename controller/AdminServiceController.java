package com.Tim401_6.Broker.controller;

import com.Tim401_6.Broker.model.Service;
import com.Tim401_6.Broker.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AdminServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping(path="/service/{id}")
    public Optional<Service> getService(@PathVariable(value = "id") int serviceId) {
        return serviceRepository.findById(serviceId);
    }

    @PutMapping(path="/service")
    public Service addService(@RequestBody Service service) {
        return serviceRepository.save(service);
    }
}
