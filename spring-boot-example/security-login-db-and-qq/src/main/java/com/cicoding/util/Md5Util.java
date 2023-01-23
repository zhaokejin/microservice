package com.cicoding.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	public static String MD5Encode(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] md5Bytes = md.digest(str.getBytes());
			return toHexString(md5Bytes);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	private static String toHexString(byte[] md5Bytes) {
		StringBuilder sb = new StringBuilder();
		byte[] arrayOfByte = md5Bytes;
		int j = md5Bytes.length;
		for (int i = 0; i < j; i++) {
			byte b = arrayOfByte[i];
			int n = b;
			if (n < 0)
				n += 256;
			int d1 = n / 16;
			int d2 = n % 16;
			sb.append(hexDigits[d1]);
			sb.append(hexDigits[d2]);
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		System.out.println(MD5Encode("1"));
	}
}
