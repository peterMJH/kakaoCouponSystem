package com.peterMoon.kakaoPay.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peterMoon.kakaoPay.entity.Coupon;
import com.peterMoon.kakaoPay.enumertation.Status;

public interface CouponRepository extends JpaRepository<Coupon, Long>{
	Coupon findByCode(String code);
	List<Coupon> findByIssuance(Status issuance);
	List<Coupon> findByExpireDate(LocalDate expireDate);
	List<Coupon> findByIssuanceAndExpireDateBetween(Status issuance, LocalDate startDatetime, LocalDate endDatetime);
}
