package com.easyshop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	/*
	 * 获取系统时间
	 */
	public static String getNow() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
}
