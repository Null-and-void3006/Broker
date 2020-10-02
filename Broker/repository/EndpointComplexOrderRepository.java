package com.Tim401_6.Broker.repository;

import com.Tim401_6.Broker.model.EndpointComplexOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EndpointComplexOrderRepository extends CrudRepository<EndpointComplexOrder, Integer> {
    @Query("SELECT e FROM EndpointComplexOrder e WHERE e.parentEndpointId = :parentEndpointId")
    List<EndpointComplexOrder> findChildEndpoints(@Param("parentEndpointId") int parentEndpointId);

}
