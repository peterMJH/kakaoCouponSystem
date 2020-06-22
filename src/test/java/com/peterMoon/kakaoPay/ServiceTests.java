package com.peterMoon.kakaoPay;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.peterMoon.kakaoPay.dto.CouponDTO;
import com.peterMoon.kakaoPay.entity.Coupon;
import com.peterMoon.kakaoPay.service.CouponService;

@SpringBootTest
public class ServiceTests {

	@Autowired
	private CouponService couponService;
	
	private int couponCount = 20;
	
	@BeforeEach
	public void init() {
		CouponDTO couponDTO = new CouponDTO();
		couponDTO.setCount(couponCount);
		couponService.setCoupon(couponDTO);
	}
	
	@Test
	public void setCoupon_test() {
		List<Coupon> coupons = couponService.getAll();
		assertEquals(couponCount, coupons.size());
	}
	
	@Test
	public void setPublishCoupon_test() {
		String publishCode = couponService.setIssuanceCoupon();
		Coupon coupon = couponService.getCouponByCode(publishCode);
		//assertEquals("Y", coupon.getStatus()); 
	}
	
	@Test
	public void getPublishCoupons_test() {
		int setStatusYCount = 3;
		for(int i=0; i<setStatusYCount; i++) {
			couponService.setIssuanceCoupon();
		}
		
		List<Coupon> coupons = couponService.getIssuanceCoupons();
		assertEquals(setStatusYCount, coupons.size());
	}
	
	@Test
	public void setUseCoupon_test() {
	}
	
	@Test
	public void getExpiredCoupons_test() {
		
	}
}
