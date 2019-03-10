package com.kefelan.messaging.kefelanmicroservicesauth.entity;

import org.springframework.data.annotation.Id;

public class Account {
    @Id
    private String id;         
    private String userName;    
    private String passWord;   
    private String[] roles;     

    
    public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(String id, String userName, String passWord, String[] roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.roles = roles;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
