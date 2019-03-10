package com.kefelan.messaging.kefelanmicroservicesauth.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kefelan.messaging.kefelanmicroservicesauth.entity.User;
import com.kefelan.messaging.kefelanmicroservicesauth.repository.UserRepository;

@RestController
public class UserController {

	private static String SERVICE_USER = "user";
	
	private static String SERVICE_NOTE = "note";
	
//	@Autowired
//    private AccountRepository accountRepository;    
	
	private UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	RestTemplate restTemplate;

//    @Autowired
//    public void init(){
//
//        accountRepository.deleteAll();
//
//        Account accountA = new Account();
//        accountA.setUserName("admin");
//        accountA.setPassWord("admin");
//        accountA.setRoles(new String[]{"ROLE_ADMIN","ROLE_USER"});
//        accountRepository.save(accountA);
//
//        Account accountB = new Account();
//        accountB.setUserName("guest");
//        accountB.setPassWord("pass123");
//        accountB.setRoles(new String[]{"ROLE_GUEST"});
//        accountRepository.save(accountB);
//    }

    @GetMapping("/user/username/{username}")
	public User[] findAllUsers(@PathVariable("username") final String userName){
		//User[] userList = restTemplate.getForObject("http://"+SERVICE_USER+"/user/username/{userName}", User[].class, userName);
		User[] userList = restTemplate.getForObject("http://"+SERVICE_NOTE+"/note/user/username/{userName}", User[].class, userName);
		
		
		return userList;
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
    
    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }
}
