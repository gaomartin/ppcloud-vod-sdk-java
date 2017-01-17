/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.http;

import com.pplive.ppcloud.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParamBean;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.Security;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author chaogao
 *
 */
public class HttpClientManager {
	
	public static HttpClientManager getInstance() {
		return SingletonHolder.instance;
	}
	
	private static class SingletonHolder {
		private static HttpClientManager instance = new HttpClientManager(20000,50000);
	}
	
    private PoolingClientConnectionManager cm = new PoolingClientConnectionManager();
    private DefaultHttpClient httpClient;
    private static final String defaultCharsetStr = "UTF-8";
    private static final Charset defaultCharset = Charset.forName(defaultCharsetStr);
    private static final String jsonContentType = "application/json; charset=UTF-8";
    private static final Integer BUFFER_SIZE = Integer.valueOf(4048);

    public HttpClientManager(int connectionTimeOut, int soTimeOut) {
    	SyncBasicHttpParams params = new SyncBasicHttpParams();
        DefaultHttpClient.setDefaultHttpParams(params);
        HttpConnectionParamBean paramsBean = new HttpConnectionParamBean(params);
        paramsBean.setConnectionTimeout(connectionTimeOut);
        paramsBean.setSoTimeout(soTimeOut);
        this.httpClient = new DefaultHttpClient(this.cm, params);
        this.httpClient.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy() {
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepalive = super.getKeepAliveDuration(response, context);
                if(keepalive == -1L) {
                    keepalive = 5000L;
                }

                return keepalive;
            }
        });
        this.httpClient.setReuseStrategy(new DefaultConnectionReuseStrategy() {
            public boolean keepAlive(HttpResponse response, HttpContext context) {
                boolean keekAlive = false;
                if(200 == response.getStatusLine().getStatusCode()) {
                    keekAlive = super.keepAlive(response, context);
                }

                return keekAlive;
            }
        });
        Security.setProperty("networkaddress.cache.ttl", "10");
    }

    private void addContentType(HttpRequestBase request, String contentType) {
        request.addHeader("Content-Type", contentType);
    }

    public String execGetRequestWithHeader(String url, Map<String, String> header) {
        return execGetRequestWithParamsAndHeaders(url, null, header, null);
    }

    public String execGetRequestWithHeader(String url, Map<String, String> header, HttpProxyConfig proxyConfig) {
        if (proxyConfig == null || StringUtils.isEmpty(proxyConfig.getProxyHost()) || proxyConfig.getProxyPort() <= 0) {
            return execGetRequestWithHeader(url, header);
        } else {
            return execGetRequestWithParamsAndHeaders(url, null, header, proxyConfig);
        }
    }

    public String execGetRequestWithHeader(String url, Map<String, String> header, String proxyHost, int proxyPort) {
        if (StringUtils.isEmpty(proxyHost) || proxyPort <= 0) {
            return execGetRequestWithHeader(url, header);
        } else {
            return execGetRequestWithParamsAndHeaders(url, null, header, new HttpProxyConfig(proxyHost, proxyPort));
        }
    }

    public String execGetRequestWithParamsAndHeaders(String url, Map<String, String> params, Map<String, String> heads, HttpProxyConfig proxyConfig) {
        HttpGet request = null;

        String res;
        try {
            if(!url.endsWith("?") && !url.contains("?")) {
                url = url + "?";
            }

            ArrayList<BasicNameValuePair> e = new ArrayList<BasicNameValuePair>();
            if(params != null && !params.isEmpty()) {
                Iterator<?> response = params.entrySet().iterator();

                while(response.hasNext()) {
                    Entry<?, ?> paramString = (Entry<?, ?>)response.next();
                    e.add(new BasicNameValuePair((String)paramString.getKey(), (String)paramString.getValue()));
                }
            }

            String paramString1 = URLEncodedUtils.format(e, defaultCharsetStr);
            url = url + paramString1;

            try {
                request = new HttpGet(new URI(url));
            } catch (URISyntaxException var18) {
                throw new RuntimeException("http get error. url: " + url, var18);
            }

            if(heads != null && !heads.isEmpty()) {
                Iterator<?> status = heads.entrySet().iterator();

                while(status.hasNext()) {
                    Entry<?, ?> response1 = (Entry<?, ?>)status.next();
                    request.addHeader((String)response1.getKey(), (String)response1.getValue());
                }
            }

            if (proxyConfig != null) {
                HttpHost proxy = new HttpHost(proxyConfig.getProxyHost(), proxyConfig.getProxyPort());
                this.httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
            }
            HttpResponse response2 = this.httpClient.execute(request);
            int status1 = response2.getStatusLine().getStatusCode();
            HttpEntity entity;
            if(status1 != 200) {
                entity = response2.getEntity();
                String reason = null;
                if(entity != null) {
                    reason = EntityUtils.toString(entity, defaultCharset);
                    System.out.println("get error, status:"+status1+", resonpse:"+reason+", url:"+url);
                } else {
                    System.out.println("get error, status:"+status1+", url:"+url);
                }

                throw new RuntimeException(String.format("http get error. url: %s, reason: %s", url, reason));
            }

            entity = response2.getEntity();
            res = EntityUtils.toString(entity, defaultCharset);
        } catch (IOException var19) {
            throw new RuntimeException("http get error. url: " + url, var19);
        } catch (RuntimeException var21) {
            throw new RuntimeException("http get error. url: " + url, var21);
        } finally {
            if(request != null) {
                request.abort();
            }

        }

        return res;
    }

    public <T> String execPostRequestWithHeaders(URI uri, Map<String, String> headers, T obj) {
        return execPostRequestWithHeaders(uri, headers, obj, null);
    }

    public <T> String execPostRequestWithHeaders(URI uri, Map<String, String> headers, T obj, String proxyHost, int proxyPort) {
        if (StringUtils.isEmpty(proxyHost) || proxyPort <= 0) {
            return execPostRequestWithHeaders(uri, headers, obj);
        } else {
            return execPostRequestWithHeaders(uri, headers, obj, new HttpProxyConfig(proxyHost, proxyPort));
        }
    }

    public <T> String execPostRequestWithHeaders(URI uri, Map<String, String> headers, T obj, HttpProxyConfig proxyConfig) {
        HttpPost request = new HttpPost(uri);
        addContentType(request, jsonContentType);
        if(headers != null && !headers.isEmpty()) {
            Iterator<?> e = headers.entrySet().iterator();

            while(e.hasNext()) {
                Entry<?, ?> json = (Entry<?, ?>)e.next();
                request.addHeader((String)json.getKey(), (String)json.getValue());
            }
        }

        String json1 = JsonUtils.toJsonString(obj);

        String res;
        try {
            StringEntity e1 = new StringEntity(json1, defaultCharsetStr);
            request.setEntity(e1);
            if (proxyConfig != null) {
                HttpHost proxy = new HttpHost(proxyConfig.getProxyHost(), proxyConfig.getProxyPort());
                this.httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
            }
            HttpResponse response = this.httpClient.execute(request);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if(status != 200) {
                String reason = null;
                if(entity != null) {
                    reason = EntityUtils.toString(entity, defaultCharset);
                    System.out.println("post error, status:"+status+", resonpse:"+reason+", url:"+uri+", requestcontent:"+json1);
                } else {
                    System.out.println("post error, status:"+status+", url:"+uri+", requestcontent:"+json1);
                }

                throw new RuntimeException(String.format("http post error. url: %s, reason: %s", uri, reason));
            }

            res = EntityUtils.toString(entity, defaultCharset);
        } catch (IOException var17) {
            throw new RuntimeException("http post error. url: " + uri, var17);
        } catch (RuntimeException var19) {
            throw new RuntimeException("http post error. url: " + uri, var19);
        } finally {
            request.abort();
        }

        return res;
    }

    public DefaultHttpClient getHttpClient() {
        return this.httpClient;
    }

    public static String getDefaultcharsetstr() {
        return defaultCharsetStr;
    }

    public static Charset getDefaultcharset() {
        return defaultCharset;
    }

    public static Integer getBufferSize() {
        return BUFFER_SIZE;
    }
}
