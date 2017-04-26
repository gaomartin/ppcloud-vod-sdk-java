package com.pplive.ppcloud.utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FeathureUtil {

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
			'c', 'd', 'e', 'f' };

	public static String getPPFeature(String path) throws IOException, NoSuchAlgorithmException {
		String result = "";
		String dig = "";
		long size = 0;
		RandomAccessFile ranFile = null;
		try {
			ranFile = new RandomAccessFile(path, "r");
			size = ranFile.length();
			MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
			if (size < 0xFFFF) {
				messageDigest.update(ranFile.readByte());
			} else {
				byte[] each_read = new byte[0x3000];
				ranFile.read(each_read);
				messageDigest.update(each_read);

				ranFile.seek(size / 5);
				ranFile.read(each_read);
				messageDigest.update(each_read);

				ranFile.seek(2 * size / 5);
				ranFile.read(each_read);
				messageDigest.update(each_read);

				ranFile.seek(3 * size / 5);
				ranFile.read(each_read);
				messageDigest.update(each_read);

				ranFile.seek(size - 0x3000);
				ranFile.read(each_read);
				messageDigest.update(each_read);
			}
			dig = getFormattedText(messageDigest.digest());
		} catch (IOException e) {
			throw e;
		} catch (NoSuchAlgorithmException e) {
			throw e;
		} finally {
			try {
				ranFile.close();
			} catch (Exception e) {
				//ignore
			}
		}
		result = String.format("%d_%s", size, dig);
		return result;
	}

	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

}
