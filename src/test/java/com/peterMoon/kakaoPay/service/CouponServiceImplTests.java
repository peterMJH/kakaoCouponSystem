package com.peterMoon.kakaoPay.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.peterMoon.kakaoPay.dto.CouponDTO;
import com.peterMoon.kakaoPay.entity.Coupon;
import com.peterMoon.kakaoPay.enumertation.Status;
import com.peterMoon.kakaoPay.repository.CouponRepository;
import com.peterMoon.kakaoPay.utils.CouponUtils;

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
					.expireDate(new Date())
					.issuance(Status.N)
					.use(Status.N)
					.build();
		Coupon coupon2 = Coupon.builder()
				.id(2L)
				.code("ASDF5678")
				.expireDate(new Date())
				.issuance(Status.Y)
				.use(Status.Y)
				.build();
		
		List<Coupon> coupons = Arrays.asList(coupon1, coupon2);
		List<Coupon> statusYCoupons = Arrays.asList(coupon2);
		
		Mockito.when(couponRepository.findByIssuance(Status.Y)).thenReturn(statusYCoupons);
		Mockito.when(couponRepository.findByExpireDate(new Date())).thenReturn(coupons);
		
	}
	
	@Test
	public void findByIssuance() {
		Coupon coupon = Coupon.builder()
				.id(2L)
				.code("ASDF5678")
				.expireDate(new Date())
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
		Coupon coupon = Coupon.builder()
				.id(2L)
				.code("ASDF5678")
				.expireDate(new Date())
				.issuance(Status.Y)
				.use(Status.Y)
				.build();
		
		List<Coupon> coupons = couponService.getExpiredCoupons();
		
		Mockito.verify(couponRepository, VerificationModeFactory.times(1)).findByExpireDate(new Date());
        Mockito.reset(couponRepository);
        
        assertThat(coupons).hasSize(1);
        assertEquals(coupons.get(0).getCode(), coupon.getCode());
	}
}
