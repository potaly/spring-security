package com.potaly.dto;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;

import validator.MyConstraint;

/**
 * 
 * @author woodman
 * @date 2019年8月12日
 */
public class User {
	
	public interface userSimpleView{};
	public interface  userDetailView extends  userSimpleView{};
	
	private String id;
	@MyConstraint(message="这是一个测试")
	private String userName;
	
	@NotBlank(message="密码不能为空！")
	private String password;
	@Past(message="生日必须是过去的时间")
	private Date birthDay;

	@JsonView(userSimpleView.class)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@JsonView(userDetailView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@JsonView(userSimpleView.class)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@JsonView(userSimpleView.class)
	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
}
