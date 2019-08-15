package com.potaly.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.potaly.exception.UserNotExistException;
/**
 * 
 * @author potaly
 * @date 2019年8月13日
 */
@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String,Object>  handleUserNotExistException(UserNotExistException userException){
		Map<String,Object> map = new HashMap<>();
		map.put("id",userException.getId());
		map.put("message", userException.getMessage());
		return map;
	}
}
