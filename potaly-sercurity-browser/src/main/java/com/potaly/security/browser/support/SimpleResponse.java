package com.potaly.security.browser.support;
/**
 * @author wang.qiang
 * @date 2019年8月14日
 */
public class SimpleResponse {
	
	public SimpleResponse(Object content){
		this.content = content;
	}
	
	private Object content;

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
}

