package com.kefelan.messaging.kefelanmicroservicesauth.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kefelan.messaging.kefelanmicroservicesauth.entity.Account;
import com.kefelan.messaging.kefelanmicroservicesauth.repository.UserRepository;

@Service
public class DomainUserDetailsService implements UserDetailsService {

//    @Autowired
//    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	//Account account = accountRepository.findByUserName(username);
    	
    	List<com.kefelan.messaging.kefelanmicroservicesauth.entity.User> userList = userRepository.findByUserName(username);
    	//com.kefelan.messaging.kefelanmicroservicesauth.entity.User[] userArray = new com.kefelan.messaging.kefelanmicroservicesauth.entity.User[userList.size()];
    	com.kefelan.messaging.kefelanmicroservicesauth.entity.User user = userList.get(0);
    	String[] roles = {"ROLE_ADMIN","ROLE_USER"};
    	Account account = new Account();
    	if(user!=null) {
    		account = new Account(user.getId().toString(), user.getUserName(), user.getPassword(), roles);
    	}
    	
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        if (account!=null){
            return new User(account.getUserName(),encoder.encode(account.getPassWord()), AuthorityUtils.createAuthorityList(account.getRoles()));
        }else {
            throw  new UsernameNotFoundException("User ["+username+"] does not exist");
        }
    }
}
