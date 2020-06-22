package com.peterMoon.kakaoPay.enumertation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
	Y("Y", "사용"),
	N("N", "미사용");
	
	private String code;
	private String desc;
}
