package com.kefelan.messaging.kefelanmicroservicesuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kefelan.messaging.kefelanmicroservicesuser.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public List<User> findByUserName(String userName);
	
}
