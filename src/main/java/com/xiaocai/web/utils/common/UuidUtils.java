package com.xiaocai.web.utils.common;

import java.util.UUID;

public class UuidUtils {

	public static String getUUID_36(){
		return UUID.randomUUID().toString();
	}
	
	public static String getUUID_32(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(getUUID_36());
		System.out.println(getUUID_32());
	}
}
