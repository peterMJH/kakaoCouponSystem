package com.peterMoon.kakaoPay.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peterMoon.kakaoPay.entity.Coupon;
import com.peterMoon.kakaoPay.repository.CouponRepository;
import com.peterMoon.kakaoPay.utils.RandomString;

@Service
public class CouponServiceImpl implements CouponService{
	
	@Autowired
	private CouponRepository couponRepository;
	
	@Override
	@Transactional
	public void setCoupon(int count) {
		RandomString rs = new RandomString(16);
		for(int i=0; i<count; i++) {
			Coupon coupon = new Coupon();
			coupon.setCode(rs.createCoupon());
			coupon.setExpireDate(new Date());
			couponRepository.save(coupon);
		}
	}
	
	@Override
	public String setPublishCoupon() {
		List<Coupon> coupons = couponRepository.findByStatus("N");
		Coupon coupon = coupons.get(0);
		coupon.setStatus("Y");
		return couponRepository.save(coupon).getCode();
	}
	
	@Override
	public List<Coupon> getPublishCoupons() {
		return couponRepository.findByStatus("Y");
	}
	
	@Override
	public void setUseCoupon(String code, String useYN) {
		Coupon coupon = couponRepository.findByCode(code);
		coupon.setUse(useYN);
		couponRepository.save(coupon);
	}
	
	@Override
	public List<Coupon> getExpiredCoupons() {
		Date date = new Date();
		return couponRepository.findByExpireDate(date);
	}
	
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
