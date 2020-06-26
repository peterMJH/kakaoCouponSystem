package com.peterMoon.kakaoPay.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.peterMoon.kakaoPay.enumertation.Status;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Coupon {
	
	// ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	// 쿠폰 코드
	@Column(name = "code", nullable = false, unique = true)
	private String code;
	
	// 만료 일자
	@Column(name = "expire_date", nullable = false, columnDefinition = "DATE")
	private LocalDate expireDate;
	
	// 지급 여부
	@Column(name = "issuance")
	private Status issuance = Status.N;
	
	// 사용 여부
	@Column(name = "use")
	@Enumerated(EnumType.STRING)
	private Status use = Status.N;
	
	// 쿠폰 지급 받은 사용자 메일
	@Column(name = "mail")
	private String mail;
	
	@Builder
	public Coupon(Long id, String code, LocalDate expireDate, Status issuance, Status use, String mail) {
		this.id = id;
		this.code = code;
		this.expireDate = expireDate;
		this.issuance = issuance;
		this.use = use;
		this.mail = mail;
	}
}
