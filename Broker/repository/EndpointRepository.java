package com.Tim401_6.Broker.repository;


import java.util.ArrayList;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Tim401_6.Broker.model.Endpoint;


public interface EndpointRepository extends CrudRepository<Endpoint, Integer> {
	default //we need to find the id of the parameter type from its name
	int getTypeFromName(String name)
	{
		ArrayList<Endpoint> a = (ArrayList<Endpoint>) findAll();
		for (Endpoint e : a)
		{
			if (e.getEndpointName().equals(name))
				return e.getId();
		}
		return -1;
	}

	List<Endpoint> findFirstByEndpointName(String name);

	@Query("SELECT e FROM Endpoint e WHERE e.endpointName = :end AND e.serviceId = :service")
	List<Endpoint> findRoute(@Param("end") String endpointName, @Param("service") int service);
}
