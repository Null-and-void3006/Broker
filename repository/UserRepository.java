package com.Tim401_6.Broker.repository;

import com.Tim401_6.Broker.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findFirstByUsername(String username);

    List<User> findFirstByServiceId(int serviceId);
}

