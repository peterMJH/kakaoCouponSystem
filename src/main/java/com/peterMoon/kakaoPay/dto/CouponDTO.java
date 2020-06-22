package com.peterMoon.kakaoPay.dto;

import com.peterMoon.kakaoPay.enumertation.Status;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CouponDTO {
	private int count;
	private String code;
	private Status useStatus; 
}
