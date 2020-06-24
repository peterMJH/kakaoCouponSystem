package com.peterMoon.kakaoPay.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import com.peterMoon.kakaoPay.enumertation.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "code", nullable = false, unique = true)
	private String code;
	
	@Column(name = "expire_date", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.DATE)
	private Date expireDate;
	
	@Column(name = "issuance")
	@Enumerated(EnumType.STRING)
	private Status issuance = Status.N;
	
	@Column(name = "use")
	@Enumerated(EnumType.STRING)
	private Status use = Status.N;
	
	@Column(name = "mail")
	private String mail;
	
	@Builder
	public Coupon(Long id, String code, Date expireDate, Status issuance, Status use) {
		this.id = id;
		this.code = code;
		this.expireDate = expireDate;
		this.issuance = issuance;
		this.use = use;
	}
}
