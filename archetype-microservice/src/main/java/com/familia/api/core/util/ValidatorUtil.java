package com.familia.api.core.util;

public class ValidatorUtil {

	private ValidatorUtil() {

	}

	public static boolean isObjectNull(Object obj) {
		return obj == null;
	}

	public static boolean isNullOrEmpty(String text) {
		return text == null || text.trim().isEmpty();
	}
	
}
