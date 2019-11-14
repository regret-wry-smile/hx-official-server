package com.hx.common.utils;
/**
 * 获取32位的UUID
 */
public class UUID {
	/**
	 * 获取32位UUID(永不重复)
	 * @return String
	 */
	 public static String get32UUID() {
		 String ss=java.util.UUID.randomUUID().toString();
		 ss= ss.replaceAll("-", "");
		 return ss;
	 }
	 /**
	  * 获取8位UUID(可能重复)
	  * @return String
	  */
	 public static String get8UUID() {
		 String ss=get32UUID();
		 return ss.substring(ss.length()-8, ss.length());
	 }
}
