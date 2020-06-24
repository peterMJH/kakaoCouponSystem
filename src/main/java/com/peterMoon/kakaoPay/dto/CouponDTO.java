package com.peterMoon.kakaoPay.dto;

import java.util.Date;

import com.peterMoon.kakaoPay.enumertation.Status;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@NoArgsConstructor
public class CouponDTO {
	private int count;
	private Status useStatus; 
	private String mail;
	private Status issuance;
	private Date startDate;
	private Date endDate;
	@Builder
	public CouponDTO(int count, Status useStatus, String mail) {
		this.count = count;
		this.useStatus = useStatus;
		this.mail = mail;
	}
}
