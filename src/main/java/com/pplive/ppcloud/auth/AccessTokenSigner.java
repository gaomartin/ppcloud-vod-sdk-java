/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.auth;

import com.pplive.ppcloud.AuthConstants;
import com.pplive.ppcloud.CharsetConstants;
import com.pplive.ppcloud.utils.HmacSHA1Util;
import com.pplive.ppcloud.utils.JsonUtils;
import com.pplive.ppcloud.utils.LogUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

import static com.pplive.ppcloud.CharsetConstants.*;


/**
 * @author chaogao
 *
 */
public class AccessTokenSigner {
	
	/**
	 * 获取单例对象
	 * @return 单例对象
	 */
	public static AccessTokenSigner getInstance() {
		return SingletonHolder.instance;
	}
	
	private static class SingletonHolder {
		private static AccessTokenSigner instance = new AccessTokenSigner();
	}
	
	private AuthCredentials authCredentials;
	private static String ACCESSTOKEN;
	
	private static Long DEAD_LINE;

	/**
	 * 默认使用常量里配置的accessKey、accessKey
	 * 实例化该类后也可以调用 {@link #setAuthCredentials} 重设accessKey、accessKey
	 */
	private AccessTokenSigner() {
		authCredentials = new AuthCredentials();
		authCredentials.setAccessKey(AuthConstants.AUTH_AK);
		authCredentials.setSecretKey(AuthConstants.AUTH_SK);
	}

	public String sign() {
		return this.sign(this.authCredentials);
	}
	
	/**
	 * 计算签名结果
	 * @param authCredentials 授权信息
	 * @return AccessToken
	 */
	public String sign(AuthCredentials authCredentials) {
		if (StringUtils.isEmpty(authCredentials.getAccessKey()) 
				|| StringUtils.isEmpty(authCredentials.getSecretKey())) {
			throw new IllegalArgumentException("empty accessKey or secretKey");
		}
		
		Date expireDate = DateUtils.addMinutes(new Date(), authCredentials.getExpireInMinutes());
		DEAD_LINE = expireDate.getTime()/1000;
		AuthorizationRequest authorizationRequest = new AuthorizationRequest();
		authorizationRequest.setRid(UUID.randomUUID().toString().replace("-", "").toLowerCase());
		authorizationRequest.setDeadline(DEAD_LINE);
		
		String json = JsonUtils.toJsonString(authorizationRequest);
		LogUtils.log(String.format("json: %s", json));
		
		String encodeJsonBase64 = null;
		try {
			encodeJsonBase64 = new String(Base64.encodeBase64(json.getBytes(CharsetConstants.DEFAULT_CHARSET), false, true));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(String.format("system unsupport encoding %s", CharsetConstants.DEFAULT_CHARSET));
		}
		
		byte[] sign = null;
		try {
			sign = HmacSHA1Util.getSignatureBytes(encodeJsonBase64, authCredentials.getSecretKey());
		} catch (Exception e) {
			throw new RuntimeException("sha1 sign error");
		}
		String encode_sign = new String(Base64.encodeBase64(sign, false, true));
		LogUtils.log(String.format("encode_sign: %s", encode_sign));
		
		String accessToken = String.format("%s:%s:%s", authCredentials.getAccessKey(), encode_sign, encodeJsonBase64);
		LogUtils.log(String.format("token: %s", accessToken));
		
		return accessToken;
	}
	
	/**
	 * 获取  AccessToken
	 * @return AccessToken
	 */
	public String getAccessToken(){
		if(null != DEAD_LINE && StringUtils.isNotEmpty(ACCESSTOKEN)
				&& (System.currentTimeMillis()/1000+300)<DEAD_LINE) {
			return ACCESSTOKEN;
		}

		ACCESSTOKEN = AccessTokenSigner.getInstance().sign(authCredentials);
		return ACCESSTOKEN;
	}

	/**
	 * 获取  AccessToken
	 * @param expireInMinutes 过期时长
	 * @return AccessToken
	 */
	public String getAccessToken(Integer expireInMinutes){
		Integer originMinutes = authCredentials.getExpireInMinutes();
		if (expireInMinutes != null && expireInMinutes > 0) {
			authCredentials.setExpireInMinutes(expireInMinutes);
		}
		ACCESSTOKEN = AccessTokenSigner.getInstance().sign(authCredentials);
		authCredentials.setExpireInMinutes(originMinutes);
		return ACCESSTOKEN;
	}
	
	public AuthCredentials getAuthCredentials() {
		return authCredentials;
	}
	public void setAuthCredentials(AuthCredentials authCredentials) {
		this.authCredentials = authCredentials;
	}
	
}
