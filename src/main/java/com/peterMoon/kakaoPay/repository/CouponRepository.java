package com.peterMoon.kakaoPay.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peterMoon.kakaoPay.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long>{
	Coupon findByCode(String code);
	List<Coupon> findByStatus(String status);
	List<Coupon> findByExpireDate(Date expireDate);
}
