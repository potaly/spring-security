package com.potaly.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.potaly.dto.User;
import com.potaly.dto.UserQueryCondition;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * 
 * @author potaly
 * @date 2019年8月13日
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/me")
	public Object getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	@GetMapping("/me2")
	public Object getCurrentUser2(@org.springframework.security.core.annotation.AuthenticationPrincipal UserDetails userdetail) {
		return  userdetail;
	}
	@GetMapping
	@JsonView(User.userSimpleView.class)
	@ApiOperation("用户查询接口")
	public List<User> query(UserQueryCondition condition,@PageableDefault(page=2,size=7,sort="userName,desc")Pageable pageable){
		System.out.println("=========================");
		System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));
		
		List<User> users = new ArrayList<>();
		User u = new User(); u.setUserName(condition.getUserName());
		users.add(u); users.add(new User()); users.add(new User());
		return users;
	}
	 
	/*@GetMapping("/{id:\\d+}")
	@JsonView(User.userDetailView.class)
	public User getInfo(@PathVariable(name="id")String id) {
		User user = new User();
		user.setUserName("tom");
		return user;
	}
	*/
	 @GetMapping("/{id:\\d+}")
	@JsonView(User.userDetailView.class)
	public User getInfo(@ApiParam("用户主键id") @PathVariable(name="id")String id) {
		throw new RuntimeException(id);
		//throw new UserNotExistException(id);
	} 
	
	
	@PostMapping
	public User postInfo(@Valid @RequestBody User condition/*,BindingResult errors*/) {
	/*	if(errors.hasErrors()) {
			errors.getAllErrors().forEach(error->System.out.println(error.getDefaultMessage()));
		}
		*/
		System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
		User user= new User();
		user.setId("1");
		user.setUserName(condition.getUserName());
		return user;
	}
	@PutMapping("/{id:\\d+}")
	public User putInfo(@Valid @RequestBody User condition,BindingResult errors) {
		if(errors.hasErrors()) {
			errors.getAllErrors().forEach(error->
			{
				FieldError err = (FieldError) error;
				String message = err.getField()+"===="+err.getDefaultMessage();
				System.out.println(message);
			});
		}
		
		System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
		User user= new User();
		user.setId("1");
		user.setUserName(condition.getUserName());
		return user;
	}
}
