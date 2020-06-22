package com.peterMoon.kakaoPay.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peterMoon.kakaoPay.dto.CouponDTO;
import com.peterMoon.kakaoPay.entity.Coupon;
import com.peterMoon.kakaoPay.enumertation.Status;
import com.peterMoon.kakaoPay.repository.CouponRepository;
import com.peterMoon.kakaoPay.utils.RandomString;

@Service
public class CouponServiceImpl implements CouponService{
	
	@Autowired
	private CouponRepository couponRepository;
	
	@Override
	@Transactional
	public void setCoupon(CouponDTO couponDTO) {
		RandomString rs = new RandomString(16);
		for(int i=0; i<couponDTO.getCount(); i++) {
			Coupon coupon = new Coupon();
			coupon.setCode(rs.createCoupon());
			coupon.setExpireDate(new Date());
			couponRepository.save(coupon);
		}
	}
	
	@Override
	public String setIssuanceCoupon() {
		List<Coupon> coupons = couponRepository.findByIssuance(Status.N);
		Coupon coupon = coupons.get(0);
		coupon.setIssuance(Status.Y);
		return couponRepository.save(coupon).getCode();
	}
	
	@Override
	public List<Coupon> getIssuanceCoupons() {
		return couponRepository.findByIssuance(Status.Y);
	}
	
	@Override
	public void setUseCoupon(String code, CouponDTO couponDTO) {
		Coupon coupon = couponRepository.findByCode(code);
		coupon.setUse(couponDTO.getUseStatus());
		couponRepository.save(coupon);
	}
	
	@Override
	public List<Coupon> getExpiredCoupons() {
		Date date = new Date();
		return couponRepository.findByExpireDate(date);
	}
	
	/* 이후부터 요건 아님 */
	@Override
	public Optional<Coupon> getCoupon(Long id) {
		return couponRepository.findById(id);
	}
	@Override
	public Coupon getCouponByCode(String code) {
		return couponRepository.findByCode(code);
	}
	@Override
	public List<Coupon> getAll() {
		return couponRepository.findAll();
	}
	
}
