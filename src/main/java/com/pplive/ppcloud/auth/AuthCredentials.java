/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.auth;

/**
 * @author chaogao
 *
 */
public class AuthCredentials {
	
	/**
	 * AK
	 * 由聚力云发放
	 */
	private String accessKey;
	
	/**
	 * SK
	 * 由聚力云发放
	 */
	private String secretKey;

	/**
	 * 生成Token过期时间
	 * 默认60分钟
	 */
	private Integer expireInMinutes = 60;
	
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public Integer getExpireInMinutes() {
		return expireInMinutes;
	}

	public void setExpireInMinutes(Integer expireInMinutes) {
		this.expireInMinutes = expireInMinutes;
	}
}
