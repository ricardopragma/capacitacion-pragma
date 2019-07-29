package co.com.pragma.service.util;

public class StringUtil {

	public static boolean isNullOrEmpty(String text) {
		return text == null || text.trim().isEmpty();
	}
}