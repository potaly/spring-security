package com.potaly.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String Greeting(String name) {
		System.out.println("greeting hello  "+name);
		return "hello"+name;
	}

}
