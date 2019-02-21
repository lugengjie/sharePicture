package com.example.demo.common.utils;

import org.springframework.util.DigestUtils;
/**
 * 生成MD5摘要
 * @author Administrator
 *
 */
public class Md5Util {
	public static String getMd5(String str) {
		String md5Code = DigestUtils.md5DigestAsHex(str.getBytes());
		return md5Code;
	}
	
}
