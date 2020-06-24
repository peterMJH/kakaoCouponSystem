package com.peterMoon.kakaoPay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KakaoPayCouponSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakaoPayCouponSystemApplication.class, args);
	}

}
