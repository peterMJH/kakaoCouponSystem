package com.peterMoon.kakaoPay.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.peterMoon.kakaoPay.entity.Coupon;
import com.peterMoon.kakaoPay.enumertation.Status;
import com.peterMoon.kakaoPay.utils.CouponUtils;

@DataJpaTest
public class CouponRepositoryTest {

	@Mock
	private CouponRepository couponRepository;
	
	@BeforeEach
	public void init() {
		CouponUtils cu = new CouponUtils(16);
		for(int i=0; i<5; i++) {
			Coupon coupon = new Coupon();
			coupon.setCode(cu.createCoupon());
			coupon.setExpireDate(new Date());
			couponRepository.save(coupon);			
		}
	}
	
	@Test
	public void findByCode() {
		List<Coupon> coupons = couponRepository.findAll();
		Coupon firstCoupon = coupons.get(0);
		Coupon coupon = couponRepository.findByCode(firstCoupon.getCode());
		
		assertEquals(firstCoupon, coupon);
	}
	
	@Test
	public void findByIssuance() {
		// Issuance default value = "N"
		List<Coupon> coupons = couponRepository.findAll();
		// search coupons (issuance = "N" )
		List<Coupon> results = couponRepository.findByIssuance(Status.N);
		
		assertEquals(coupons.size(), results.size());
	}
		
	@Test
	public void findByExpireDate() {
		// init() set expireDate = today
		List<Coupon> coupons = couponRepository.findAll();
		// search coupons (expireDate = today)
		List<Coupon> results = couponRepository.findByExpireDate(new Date());
		
		assertEquals(coupons.size(), results.size());
	}
}
