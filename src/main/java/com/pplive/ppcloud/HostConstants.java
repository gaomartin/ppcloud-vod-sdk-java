/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud;

/**
 * @author chaogao
 *
 */
public class HostConstants {
	
	/**
	 * 服务域名 
	 */
	public static final String HOST_URL = "http://svc.pptvyun.com";
	
	/**
	 * m3u8播放地址
	 */
	public static final String M3U8_PLAY_URL = "http://player.pptvyun.com/svc/m3u8player/pl/%s.m3u8";
	
	/**
	 * 新增一个视频分类
	 */
	public static final String ADD_CATEGORY_URL = "/svc/api3/category";
	
	/**
	 * 修改用户分类信息
	 */
	public static final String UPDATE_CATEGORY_URL = "/svc/api3/category/%s";
	
	/**
	 * 删除一个用户分类
	 */
	public static final String DELETE_CATEGORY_URL = "/svc/api3/category/%s/delete";
	
	/**
	 * 获取视频分类列表
	 */
	public static final String CATEGORY_LIST_URL = "/svc/api3/category/list";
	
	/**
	 * 上传视频
	 */
	public static final String UPLODAD_CHANNEL_URL = "/svc/api3/channel";
	
	/**
	 * 获取单个视频信息
	 */
	public static final String CHANNEL_INFO_URL = "/svc/api3/channel/%s";
	
	/**
	 * 获取视频列表
	 */
	public static final String CHANNEL_LIST_URL = "/svc/api3/channel/list";
	
	/**
	 * 修改视频信息
	 */
	public static final String UPDATE_CHANNEL_URL = "/svc/api3/channel/%s";
	
	/**
	 * 开启/屏蔽视频
	 */
	public static final String SET_CHANNEL_PLAYABLE_URL = "/svc/api3/channel/playable";

	/**
	 * 删除视频
	 */
	public static final String DELETE_CHANNEL_URL = "/svc/api3/channel/delete";
}
