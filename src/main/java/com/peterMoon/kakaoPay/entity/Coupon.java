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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "code", nullable = false, unique = true)
	private String code;
	
	@Column(name = "expire_date", nullable = false, columnDefinition = "DATE")
	private LocalDate expireDate;
	
	@Column(name = "issuance")
	private Status issuance = Status.N;
	
	@Column(name = "use")
	@Enumerated(EnumType.STRING)
	private Status use = Status.N;
	
	@Column(name = "mail")
	private String mail;
	
	@Builder
	public Coupon(Long id, String code, LocalDate expireDate, Status issuance, Status use) {
		this.id = id;
		this.code = code;
		this.expireDate = expireDate;
		this.issuance = issuance;
		this.use = use;
	}
}
