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
	
	//	랜덤한 코드 쿠폰을 N개 생성(명세조건-1번) : 만료일은 생성일 로부터 3일이후로 설정 
	@PostMapping(value = "/coupons")
	public List<Coupon> createCoupons(@RequestBody CouponDTO couponDTO) {
		return couponService.setCoupon(couponDTO);
	}
	//	생성된 쿠폰중 하나를 사용자에게 지급(명세조건 2번)
	@PutMapping(value = "/coupons")
	public String issuanceCoupon(@RequestBody CouponDTO couponDTO) {
		return couponService.setIssuanceCoupon(couponDTO);
	}
	//	사용자에게 지급된 쿠폰을 조회(명세조건 3번)
	@GetMapping(value = "/coupons")
	public List<Coupon> publishCoupons() {
		return couponService.getIssuanceCoupons();
	}
	//	지급된 쿠폰중 하나를 사용/취소(명세조건 4번/5번)
	@PutMapping(value = "/coupons/{code}/use")
	public Coupon useCoupon(
			@PathVariable String code,
			@RequestBody CouponDTO couponDTO
			) {
		return couponService.setUseCoupon(code, couponDTO);
	}
	//	발급된 쿠폰중 당일 만료된 전체 쿠폰 목록을 조회(명세조건 6번)
	@GetMapping(value = "/coupons/expired")
	public List<Coupon> expiredCoupons() {
		return couponService.getExpiredCoupons();
	}
}
