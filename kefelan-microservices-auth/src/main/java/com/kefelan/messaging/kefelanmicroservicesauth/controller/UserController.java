package com.kefelan.messaging.kefelanmicroservicesauth.controller;

import com.kefelan.messaging.kefelanmicroservicesauth.entity.Account;
import com.kefelan.messaging.kefelanmicroservicesauth.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private AccountRepository accountRepository;    


    @Autowired
    public void init(){

        accountRepository.deleteAll();

        Account accountA = new Account();
        accountA.setUserName("admin");
        accountA.setPassWord("admin");
        accountA.setRoles(new String[]{"ROLE_ADMIN","ROLE_USER"});
        accountRepository.save(accountA);

        Account accountB = new Account();
        accountB.setUserName("guest");
        accountB.setPassWord("pass123");
        accountB.setRoles(new String[]{"ROLE_GUEST"});
        accountRepository.save(accountB);
    }

    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }
}
