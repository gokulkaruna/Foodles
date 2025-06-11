package com.cg.foodles.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "login_seq",sequenceName = "login_seq",allocationSize = 1)
@Table(name="login_tbl")
public class LoginBean {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "login_seq")
    @Column(name="usrid")
    private Integer userId;
    
    @Column(name="uname",length=20)
    private String userName;
    
    @Column(name="pwd",length=20)
    private String password;
    
    @Column(name="usr_type",length=20)
    private String userType;


    
    public LoginBean() {
    	super();
    }
    
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
	@Override
	public String toString() {
		return "LoginBean [userId=" + userId + ", userName=" + userName + ", password=" + password + ", userType="
				+ userType + "]";
	}
    
  
}
