package com.peterMoon.kakaoPay.scheduler;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.peterMoon.kakaoPay.entity.Coupon;
import com.peterMoon.kakaoPay.enumertation.Status;
import com.peterMoon.kakaoPay.repository.CouponRepository;

@Component
public class CouponScheduler {
	
	private final Logger logger = LoggerFactory.getLogger(CouponScheduler.class);
	
	@Autowired
	private CouponRepository couponRepository;
	
//	@Scheduled(cron= "0 0 0 * * ?")
	@Scheduled(cron = "0 0/1 * * * ?")
	public void expiredCouponJobSch() {
		logger.info("## expiredCouponJobSch");
 		List<Coupon> coupons = couponRepository.findByIssuanceAndExpireDateBetween(
				Status.Y, 
				LocalDate.now(), 
				LocalDate.now().plusDays(3));
		
		for (Coupon coupon : coupons) {
			LocalDate today = LocalDate.now();
			LocalDate hiredDate = coupon.getExpireDate();
			long resultDay = today.until(hiredDate, ChronoUnit.DAYS);
			
			logger.debug("## code : " + coupon.getCode() + "/ email : " + coupon.getMail() + "/ 남은 날짜 : " + resultDay);
			
		}
		
	}
}
