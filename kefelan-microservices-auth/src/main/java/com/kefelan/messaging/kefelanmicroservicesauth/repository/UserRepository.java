package com.kefelan.messaging.kefelanmicroservicesauth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kefelan.messaging.kefelanmicroservicesauth.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	public List<User> findByUserName(String userName);
	
}
