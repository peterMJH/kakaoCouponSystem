package com.peterMoon.kakaoPay.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peterMoon.kakaoPay.dto.CouponDTO;
import com.peterMoon.kakaoPay.entity.Coupon;
import com.peterMoon.kakaoPay.enumertation.Status;
import com.peterMoon.kakaoPay.repository.CouponRepository;
import com.peterMoon.kakaoPay.utils.CouponUtils;

@Service
public class CouponServiceImpl implements CouponService{
	
	@Autowired
	private CouponRepository couponRepository;
	
	@Override
	@Transactional
	public List<Coupon> setCoupon(CouponDTO couponDTO) {
		List<Coupon> results = new ArrayList<Coupon>();
		CouponUtils rs = new CouponUtils(16);
		for(int i=0; i<couponDTO.getCount(); i++) {
			Coupon coupon = Coupon.builder()
						.code(rs.createCoupon())
						.expireDate(rs.getExpireDate())
						.issuance(Status.N)
						.use(Status.N)
						.build();
			
			Coupon result = couponRepository.save(coupon);
			results.add(result);
		}
		return results;
	}
	
	@Override
	public String setIssuanceCoupon(CouponDTO couponDTO) {
		List<Coupon> coupons = couponRepository.findByIssuance(Status.N);
		if(coupons.size()>0) {
			Coupon coupon = coupons.get(0);
			coupon.setIssuance(Status.Y);
			coupon.setMail(couponDTO.getMail());
			return couponRepository.save(coupon).getCode();
		} else {
			return "item is out of stock";
		}
	}
	
	@Override
	public List<Coupon> getIssuanceCoupons() {
		return couponRepository.findByIssuance(Status.Y);
	}
	
	@Override
	public Coupon setUseCoupon(String code, CouponDTO couponDTO) {
		Coupon coupon = couponRepository.findByCode(code);
		coupon.setUse(couponDTO.getUseStatus());
		return couponRepository.save(coupon);
	}
	
	@Override
	public List<Coupon> getExpiredCoupons() {
		LocalDate date = LocalDate.now();
		return couponRepository.findByExpireDate(date);
	}
}
