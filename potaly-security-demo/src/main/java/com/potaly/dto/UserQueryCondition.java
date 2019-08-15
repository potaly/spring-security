package com.potaly.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserQueryCondition {
	@ApiModelProperty("用户年龄起始值")
	private int age;
	@ApiModelProperty("用户登录名称")
	private String userName;
	
	@ApiModelProperty("用户年龄结束值")
	private int ageto;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAgeto() {
		return ageto;
	}

	public void setAgeto(int ageto) {
		this.ageto = ageto;
	}
	
	
}
