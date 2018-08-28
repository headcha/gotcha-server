package com.seolgi.detector.domain.utils;

import org.springframework.util.StringUtils;

public final class PasswordUtil {
	private static final String SPECIAL_CHAR_PATTERN = ".*['!@#$%^*+=-].*";
	private static final String UPPER_CASE_STRING_PATTERN = ".*[A-Z].*";
	private static final String LOWER_CASE_STRING_PATTERN = ".*[a-z].*";
	private static final String NUMBER_PATTERN = ".*[0-9].*";

	private PasswordUtil() {
	}

	/**
	 * 비밀번호가 Null인 경우 NullPointerException 발생.
	 * 숫자/특수문자/대문자/소문자 중 2가지 이상의 조합, 8자 이상 30자 이하가 아닐 경우 false.
	 *
	 * @param password 비밀번호
	 */
	public static boolean isValid(String password) {
		if (StringUtils.isEmpty(password))
			return false;

		int matchesCount = 0;

		if (notValidRange(minValidLength(9), password, maxValidLength(30)))
			return false;

		if (password.matches(SPECIAL_CHAR_PATTERN))
			matchesCount++;

		if (password.matches(UPPER_CASE_STRING_PATTERN))
			matchesCount++;

		if (password.matches(LOWER_CASE_STRING_PATTERN))
			matchesCount++;

		if (password.matches(NUMBER_PATTERN))
			matchesCount++;

		return matchesCount >= 2;
	}

	private static boolean notValidRange(int min, String password, int max) {
		return validRange(min, password, max) == false;
	}

	private static boolean validRange(int min, String password, int max) {
		return min <= password.length() && password.length() <= max;
	}

	private static int minValidLength(int length) {
		return length;
	}

	private static int maxValidLength(int length) {
		return length;
	}
}
