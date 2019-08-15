package com.potaly.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	

	@Test
	public void whenUploadSuccess() throws Exception {
		String result = mockMvc.perform(fileUpload("/file")
				.file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes("UTF-8"))))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	
	@Test
	public void whenQuerySuccess() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/user").param("userName", "wangqiang")
				.param("age", "18")
				.param("ageto", "60")
				/*.param("size", "30")
				.param("page", "1")
				.param("sort", "age,desc")*/
				.contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect( status().isOk())
		.andExpect(  MockMvcResultMatchers.jsonPath("$.length()").value(3))
		.andReturn().getResponse().getContentAsString();
		System.out.println(result);
		
	}
	@Test
	public void whenGetinfoSuccess() throws Exception {
		String result = mockMvc.perform(get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(jsonPath("$.userName").value("tom")).andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	@Test
	public void whenGetFail() throws Exception{
		mockMvc.perform(get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().is4xxClientError());
		
	}
	
	@Test
	public void whenCreateSuccess() throws Exception{
		String json = "{\"userName\":\"tom\",\"password\":null}";
		mockMvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
		.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
		
	}
	@Test
	public void whenUpdateSuccess() throws Exception{
		Date date = new Date(LocalDateTime.now().plusYears(1)
				.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		String json = "{\"userName\":\"tom\",\"password\":null,\"birthDay\":"+date.getTime()+"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
		.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
		
	}
}
