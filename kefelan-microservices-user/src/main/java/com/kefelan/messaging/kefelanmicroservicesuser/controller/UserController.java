package com.kefelan.messaging.kefelanmicroservicesuser.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kefelan.messaging.kefelanmicroservicesuser.model.User;
import com.kefelan.messaging.kefelanmicroservicesuser.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping()
	public List<User> findAllUsers(){
		return  userRepository.findAll();
	}
	
	@GetMapping(value = "/{userId}")
	public User findUserById(@PathVariable("userId") Integer userId){
		return userRepository.findById(userId).orElse(null);
	}
	
	@GetMapping(value = "/username/{userName}")
	public User[] findUsersByName(@PathVariable("userName") String userName){
		List<User> userList = userRepository.findByUserName(userName);
		User[] userArray = new User[userList.size()];
		for(int i=0; i<userList.size(); i++) {
			userArray[i] = userList.get(i);
		}
		return userArray;
	}
	
	@PostMapping()
	public User createUser(@RequestBody User user){
		return userRepository.save(user);
	}
	
	@PutMapping(value = "/{userId}")
	public User updateUser(@RequestBody User user,@PathVariable("userId") Integer userId){
		if(userRepository.findById(userId).orElse(null)!=null) {
			user.setId(userId);
			return userRepository.save(user);
		}
		return null;
	}
	
	@DeleteMapping(value = "/{userId}")
	public User deleteUser(@PathVariable("userId") Integer userId){
		userRepository.deleteById(userId);
		return userRepository.findById(userId).orElse(null);
	}
}
