package com.demo.csv;

import com.opencsv.bean.CsvBindByName;

public class UserPOJO {
	
	//put same name 
    @CsvBindByName(column="username")
	private String x;
    @CsvBindByName(column="passowrd")
	private String y;

	public UserPOJO() {
		
	}
	public UserPOJO(String username, String password) {
		super();
		this.x = username;
		this.y = password;
	}

	@Override
	public String toString() {
		return "UserPOJO [username=" + x + ", password=" + y + "]";
	}

	public String getUsername() {
		return x;
	}

	public void setUsername(String username) {
		this.x = username;
	}

	public String getPassword() {
		return y;
	}

	public void setPassword(String password) {
		this.y = password;
	}

}
