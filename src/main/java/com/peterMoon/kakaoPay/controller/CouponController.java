package com.peterMoon.kakaoPay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peterMoon.kakaoPay.dto.CouponDTO;
import com.peterMoon.kakaoPay.entity.Coupon;
import com.peterMoon.kakaoPay.service.CouponService;

@RestController
@RequestMapping(value = "/api")
public class CouponController {

	@Autowired
	private CouponService couponService;
	
	@PostMapping(value = "/coupons")
	public List<Coupon> createCoupons(@RequestBody CouponDTO couponDTO) {
		return couponService.setCoupon(couponDTO);
	}
	
	@PutMapping(value = "/coupons")
	public String issuanceCoupon(@RequestBody CouponDTO couponDTO) {
		return couponService.setIssuanceCoupon(couponDTO);
	}
	
	@GetMapping(value = "/coupons")
	public List<Coupon> publishCoupons() {
		return couponService.getIssuanceCoupons();
	}
	
	@PutMapping(value = "/coupons/{code}/use")
	public Coupon useCoupon(
			@PathVariable String code,
			@RequestBody CouponDTO couponDTO
			) {
		return couponService.setUseCoupon(code, couponDTO);
	}
	
	@GetMapping(value = "/coupons/expired")
	public List<Coupon> expiredCoupons() {
		return couponService.getExpiredCoupons();
	}
}
