package com.Tim401_6.Broker.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Tim401_6.Broker.model.AuthorizationGroup;

public interface AuthorizationGroupRepository extends CrudRepository<AuthorizationGroup, Integer> {
	@Query("SELECT a FROM AuthorizationGroup a WHERE a.userId = :userId AND a.endpointId = :endpointId")
	List<AuthorizationGroup> findAuthorizationForUserForEndpoint(@Param("userId") int userId, @Param("endpointId") int endpointId);
}
