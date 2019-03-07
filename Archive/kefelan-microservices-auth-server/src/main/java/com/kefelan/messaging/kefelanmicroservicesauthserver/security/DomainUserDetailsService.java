package com.kefelan.messaging.kefelanmicroservicesauthserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kefelan.messaging.kefelanmicroservicesauthserver.entity.Account;

/**
 * 用户信息服务 实现 Spring Security的UserDetailsService接口方法，用于身份认证
 */
@Service
public class DomainUserDetailsService implements UserDetailsService {

	private static String KEFELAN_MICROSERVICES_USER = "kefelan-microservices-user";

	@Autowired
	RestTemplate restTemplate;

	/**
	 * 根据用户名查找账户信息并返回用户信息实体
	 * 
	 * @param username 用户名
	 * @return 用于身份认证的 UserDetails 用户信息实体
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Account account = accountRepository.findByUserName(username);
		com.kefelan.messaging.kefelanmicroservicesauthserver.entity.User[] userList = restTemplate.getForObject(
				"http://" + KEFELAN_MICROSERVICES_USER + "/user/username/{nameName}",
				com.kefelan.messaging.kefelanmicroservicesauthserver.entity.User[].class, username);
		Account account = new Account();
		if (userList != null && userList.length > 0) {
			com.kefelan.messaging.kefelanmicroservicesauthserver.entity.User user = userList[0];
			account.setId("1");
			account.setUserName(user.getUserName());
			account.setPassWord(user.getPassword());
			account.setRoles(new String[]{"ROLE_ADMIN","ROLE_USER"});
		}

//		if (account != null) {
			return new User(account.getUserName(), account.getPassWord(),
					AuthorityUtils.createAuthorityList(account.getRoles()));
//		} else {
//			throw new UsernameNotFoundException("用户[" + username + "]不存在");
//		}
	}
}
