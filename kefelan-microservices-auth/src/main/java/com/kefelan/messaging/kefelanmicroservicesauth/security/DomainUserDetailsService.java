package com.kefelan.messaging.kefelanmicroservicesauth.security;

import com.kefelan.messaging.kefelanmicroservicesauth.entity.Account;
import com.kefelan.messaging.kefelanmicroservicesauth.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserName(username);
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        if (account!=null){
            return new User(account.getUserName(),encoder.encode(account.getPassWord()), AuthorityUtils.createAuthorityList(account.getRoles()));
        }else {
            throw  new UsernameNotFoundException("User ["+username+"] does not exist");
        }
    }
}
