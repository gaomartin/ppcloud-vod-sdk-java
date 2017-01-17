/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.auth;

/**
 * @author chaogao
 *
 */
public class AuthorizationRequest {
	
	/**
	 * 标识请求的唯一值
	 */
	private String rid;
	
	/**
	 * 过期时间
	 * 超过时间的请求均会被拒绝
	 */
	private Long deadline;
	
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public Long getDeadline() {
		return deadline;
	}
	public void setDeadline(Long deadline) {
		this.deadline = deadline;
	}
}
