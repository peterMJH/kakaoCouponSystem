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
	
	//	발급된 쿠폰중 만료 3일전 메세지 발송 기능(명세조건 7번)
	@Scheduled(cron= "0 0 0 * * ?")
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
			
			logger.info("## code : " + coupon.getCode() + "/ email : " + coupon.getMail() + "/ 남은 날짜 : " + resultDay);
			
		}
		
	}
}
