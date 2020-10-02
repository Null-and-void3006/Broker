package com.Tim401_6.Broker.repository;

import com.Tim401_6.Broker.model.Service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ServiceRepository extends CrudRepository<Service, Integer> {
	List<Service> findFirstByServiceName(String serviceName);

	@Query("SELECT e FROM Service e WHERE e.serviceId = :serviceId")
	List<Service> findConcrete(@Param("serviceId") int serviceId);
}
