package com.kefelan.messaging.kefelanmicroservicesauthserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
*  用户信息控制器
*/
@RestController
public class UserController {

	private static String KEFELAN_MICROSERVICES_USER = "kefelan-microservices-user";
	
	@Autowired
	RestTemplate restTemplate;
	
//	@Autowired
//    private TokenStore tokenStore;
//	
//	@PostMapping("/bar")
//    public String bar(@RequestHeader("Authorization") String auth) {
// 
//        MyUserDetails userDetails = (MyUserDetails) tokenStore.readAuthentication(auth.split(" ")[1]).getPrincipal();
// 
//        User user = userDetails.getUser();
// 
//        return user.getUserName() + ":" + user.getPassword();
//    }

//	 /**
//     * 初始化用户数据
//     */
//    @Autowired
//    public void init(){
//
//        // 为了方便测试,这里添加了两个不同角色的账户
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
    
    /**
     * 获取授权用户的信息
     * @param user 当前用户
     * @return 授权信息
     */
    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }
    
//	/**
//	 * **Invoke user service**
//	 * @param userName
//	 * @return
//	 */
//	@GetMapping("/user/username/{username}")
//	public User[] findAllUsers(@PathVariable("username") final String userName){
//		User[] userList = restTemplate.getForObject("http://"+KEFELAN_MICROSERVICES_USER+"/user/username/{nameName}", User[].class, userName);
//		return userList;
//	}
}