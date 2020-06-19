package com.peterMoon.kakaoPay.utils;

import java.util.Random;

public class RandomString {

	private final char[] buf = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	
	private final char[] resultStr;
	
	public RandomString(int length) {
		if (length < 1)
			throw new IllegalArgumentException("length < 1: " + length);
		resultStr = new char[length];
	}
	
	public String createCoupon() {
		Random rd = new Random();
		for (int i=0; i<resultStr.length; i++) {
			char ch = buf[rd.nextInt(buf.length)];
			resultStr[i] = ch;
		}
		return new String(resultStr);
	}
}
