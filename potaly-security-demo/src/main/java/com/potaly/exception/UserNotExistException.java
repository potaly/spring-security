package com.potaly.exception;
/**
 * @author potaly
 * @date 2019年8月13日
 */
public class UserNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String  id;
	
	public UserNotExistException(String id) {
		super(" user not exist  ");
		this.id=id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
