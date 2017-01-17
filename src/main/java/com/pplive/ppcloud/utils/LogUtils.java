/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.utils;

import java.util.Date;

/**
 * @author chaogao
 *
 */
public class LogUtils {
	
	/**
	 * 是否开启日志
	 */
	private static boolean debug = true;	
	
	public static void log(String msg) {
		if (debug) {
			System.out.println(String.format("[%s] %s", new Date() , msg));
		}
	}
	
}
