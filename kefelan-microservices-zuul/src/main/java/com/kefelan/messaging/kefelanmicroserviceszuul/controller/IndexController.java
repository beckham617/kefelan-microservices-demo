package com.kefelan.messaging.kefelanmicroserviceszuul.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	private static String KEFELAN_MICROSERVICES_USER = "user";

	private static String KEFELAN_MICROSERVICES_NOTE = "note";
	
//	@Autowired
//	RestTemplate restTemplate;
    
	/**
	 * **Invoke user service**
	 * 
	 * @param userName
	 * @return
	 */
//	@GetMapping("/user/username/{username}")
//	public User[] findAllUsers(@PathVariable("username") final String userName) {
//
//		// User[] userList = restTemplate.getForObject("http://localhost:8302/note/user/username/{nameName}", User[].class, userName);
//		// User[] userList = restTemplate.getForObject("http://localhost:8301/user/username/{nameName}", User[].class, userName);
//		
//		User[] userList = restTemplate.getForObject("http://" + KEFELAN_MICROSERVICES_NOTE + "/note/user/username/{nameName}", User[].class, userName);
//		
//		return userList;
//		
//	}
	
    @RequestMapping("/")
    public String index(){
        return "API-GateWay";
    }
    
}
