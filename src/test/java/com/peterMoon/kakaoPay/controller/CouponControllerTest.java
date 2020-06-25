package com.peterMoon.kakaoPay.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.peterMoon.kakaoPay.dto.CouponDTO;
import com.peterMoon.kakaoPay.entity.Coupon;
import com.peterMoon.kakaoPay.enumertation.Status;

@SpringBootTest
@AutoConfigureMockMvc
public class CouponControllerTest {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@WithMockUser(username = "peter", roles = "USER")
	public void postCoupons() throws Exception {
		
		mockMvc.perform(post("/api/coupons")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(CouponDTO.builder().count(10).build())))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "peter", roles = "USER")
	public void getCoupons() throws Exception {
		
		mockMvc.perform(get("/api/coupons"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "peter", roles = "USER")
	public void putCoupons() throws Exception {
		
		mockMvc.perform(post("/api/coupons")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(CouponDTO.builder().count(10).build())));
		
		mockMvc.perform(put("/api/coupons")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(CouponDTO.builder().mail("mjh9016@gmail.com").build())))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "peter", roles = "USER")
	public void putUseStatus() throws Exception {
		
		MvcResult result = mockMvc.perform(post("/api/coupons")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(CouponDTO.builder().count(1).build())))
				.andExpect(status().isOk()).andReturn();
		
		ObjectMapper objectMapper = new ObjectMapper();
		// solution: Cannot construct instance of java.time.LocalDate
		objectMapper.registerModule(new JavaTimeModule()); 
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		List<Coupon> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Coupon>>() {});
		String code = response.get(0).getCode();
		mockMvc.perform(put("/api/coupons/"+code+"/use")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(CouponDTO.builder().useStatus(Status.Y).build())))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "peter", roles = "USER")
	public void expiredCoupons() throws Exception {
		
		mockMvc.perform(post("/api/coupons")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(CouponDTO.builder().count(5).build())));
		
		mockMvc.perform(get("/api/coupons/expired"))
				.andDo(print())
				.andExpect(status().isOk());
		
	}
	
}
