package com.peterMoon.kakaoPay.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterMoon.kakaoPay.dto.UserDTO;
import com.peterMoon.kakaoPay.entity.User;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void users() throws Exception {
		mockMvc.perform(get("/users"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void signUp() throws Exception {
		mockMvc.perform(post("/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(UserDTO.builder().username("ally").password("a12315").build())))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void signIn() throws Exception {
		String username = "peter";
		String password = "a12315";
		
		MvcResult result = mockMvc.perform(post("/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(UserDTO.builder().username(username).password(password).build())))
				.andExpect(status().isOk()).andReturn();
		
		ObjectMapper objectMapper = new ObjectMapper();
		User response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<User>() {});
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.set("username", response.getUsername());
		params.set("password", "a12315");
		
		mockMvc.perform(get("/sign-in")
				.params(params))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
}
