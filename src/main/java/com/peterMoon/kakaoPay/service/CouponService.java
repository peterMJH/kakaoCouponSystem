package com.peterMoon.kakaoPay.service;

import java.util.List;
import java.util.Optional;

import com.peterMoon.kakaoPay.dto.CouponDTO;
import com.peterMoon.kakaoPay.entity.Coupon;

public interface CouponService {
	// 쿠폰을 N개 생성
	List<Coupon> setCoupon(CouponDTO couponDTO);
	
	// 생성된 쿠폰중 하나를 사용자에게 지급
	String setIssuanceCoupon(CouponDTO couponDTO);
	
	// 사용자에게 지급된 쿠폰 목록 조회
	List<Coupon> getIssuanceCoupons();
	
	// 지급된 쿠폰중 하나를 사용 혹은 취소
	Coupon setUseCoupon(String code, CouponDTO couponDTO);
	
	// 발급된 쿠폰중 당일 만료된 전체 쿠폰 목록 조회
	List<Coupon> getExpiredCoupons();
	
	/* 이후부터 요건 아님 */
	Coupon getCouponByCode(String code);
	Optional<Coupon> getCoupon(Long id);
	List<Coupon> getAll();
}
