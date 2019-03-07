package com.kefelan.messaging.kefelanmicroservicesauth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.kefelan.messaging.kefelanmicroservicesauth.entity.Account;

@Component
public interface AccountRepository extends MongoRepository<Account, String> {

    Account findByUserName(String username);
}
