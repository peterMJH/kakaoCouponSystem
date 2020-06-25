package com.peterMoon.kakaoPay.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.peterMoon.kakaoPay.entity.Coupon;
import com.peterMoon.kakaoPay.enumertation.Status;
import com.peterMoon.kakaoPay.repository.CouponRepository;

@SpringBootTest
public class CouponServiceImplTests {

	@TestConfiguration
    static class CouponServiceImplTestsContextConfiguration {
        @Bean
        public CouponService couponService() {
            return new CouponServiceImpl();
        }
    }
	
	@Autowired
	private CouponService couponService;
	
	@MockBean
	private CouponRepository couponRepository;
	
	@BeforeEach
	public void init() {
		// set 관련 기능은 JpaRepository의 save만 사용하였으므로 repository 테스트와 중복 되어 제외
		
		Coupon coupon1 = Coupon.builder()
					.id(1L)
					.code("QWER1234")
					.expireDate(LocalDate.now())
					.issuance(Status.N)
					.use(Status.N)
					.build();
		Coupon coupon2 = Coupon.builder()
				.id(2L)
				.code("ASDF5678")
				.expireDate(LocalDate.now())
				.issuance(Status.Y)
				.use(Status.Y)
				.build();
		
		List<Coupon> todayExpiredCoupons = Arrays.asList(coupon1, coupon2);
		List<Coupon> statusYCoupons = Arrays.asList(coupon2);
		
		Mockito.when(couponRepository.findByIssuance(Status.Y)).thenReturn(statusYCoupons);
		Mockito.when(couponRepository.findByExpireDate(LocalDate.now())).thenReturn(todayExpiredCoupons);
		
	}
	
	@Test
	public void findByIssuance() {
		Coupon coupon = Coupon.builder()
				.id(2L)
				.code("ASDF5678")
				.expireDate(LocalDate.now())
				.issuance(Status.Y)
				.use(Status.Y)
				.build();
		
		List<Coupon> coupons = couponService.getIssuanceCoupons();
		
		Mockito.verify(couponRepository, VerificationModeFactory.times(1)).findByIssuance(Status.Y);
        Mockito.reset(couponRepository);
        
        assertThat(coupons).hasSize(1);
        assertEquals(coupons.get(0).getCode(), coupon.getCode());
	}
	
	@Test
	public void findByExpireDate() {
		List<Coupon> coupons = couponService.getExpiredCoupons();
		
		Mockito.verify(couponRepository, VerificationModeFactory.times(1)).findByExpireDate(LocalDate.now());
        Mockito.reset(couponRepository);
        
        assertThat(coupons).hasSize(2);
	}
}
