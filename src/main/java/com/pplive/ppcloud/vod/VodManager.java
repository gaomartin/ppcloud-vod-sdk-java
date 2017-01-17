package com.pplive.ppcloud.vod;

import com.pplive.ppcloud.HostConstants;
import com.pplive.ppcloud.VersionConstants;
import com.pplive.ppcloud.auth.AccessTokenSigner;
import com.pplive.ppcloud.http.HttpClientManager;
import com.pplive.ppcloud.http.HttpProxyConfig;
import com.pplive.ppcloud.request.*;
import com.pplive.ppcloud.response.*;
import com.pplive.ppcloud.utils.JsonUtils;
import com.pplive.ppcloud.utils.LogUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by robertpicyu on 2016/12/1.
 */
public class VodManager {

    private VodManager(){}

    public static VodManager getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static VodManager instance = new VodManager();
    }

    private static Map<String, String> headerMap = null;
    private HttpProxyConfig proxyConfig = null;

    public HttpProxyConfig getProxyConfig() {
        return proxyConfig;
    }

    public void setProxyConfig(HttpProxyConfig proxyConfig) {
        this.proxyConfig = proxyConfig;
    }

    static
    {
        headerMap = new HashMap<String, String>();
        headerMap.put("version", VersionConstants.VERSION30);
    }

    /**
     * 添加视频分类
     * @param request VodCategoryAddRequest 实体对象
     * @return VodCategoryAddResponse 实体对象
     */
    public VodCategoryAddResponse addCategory(VodCategoryAddRequest request){
        LogUtils.log(String.format("create request: %s", JsonUtils.toJsonString(request)));

        VodCategoryAddResponse response = null;
        setHeader();
        URI uri = getUri(HostConstants.HOST_URL + HostConstants.ADD_CATEGORY_URL);
        String jsonRes = HttpClientManager.getInstance().execPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
        LogUtils.log(String.format("create response: %s", jsonRes));
        if (StringUtils.isNotEmpty(jsonRes)) {
            VodCategoryAddData vodCategoryAddData = JsonUtils.fromJsonString(jsonRes, VodCategoryAddData.class);
            if ("0".equals(vodCategoryAddData.getErr())) {
                response = vodCategoryAddData.getData();
            } else {
                response = new VodCategoryAddResponse();
            }
            response.setErr(vodCategoryAddData.getErr());
            response.setMsg(vodCategoryAddData.getMsg());
        }
        return response;
    }

    /**
     * 更改视频分类
     * @param request VodCategoryUpdateRequest 实体对象
     * @return VodCategoryUpdateResponse 实体对象
     */
    public BaseResponse updateCategory(VodCategoryUpdateRequest request){
        LogUtils.log(String.format("create request: %s", JsonUtils.toJsonString(request)));

        BaseResponse response = null;
        setHeader();
        URI uri = getUri(String.format(HostConstants.HOST_URL + HostConstants.UPDATE_CATEGORY_URL,request.getCategoryId()));
        String jsonRes = HttpClientManager.getInstance().execPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
        LogUtils.log(String.format("create response: %s", jsonRes));
        if (StringUtils.isNotEmpty(jsonRes)) {
            response= JsonUtils.fromJsonString(jsonRes, BaseResponse.class);
        }
        return response;
    }

    /**
     * 删除视频分类
     * @param request 请求的实体对象
     * @return BaseResponse 删除结果
     */
    public BaseResponse deleteCategory(VodCategoryDeleteRequest request){
        LogUtils.log(String.format("create request: %s", JsonUtils.toJsonString(request)));

        BaseResponse response = null;
        setHeader();
        URI uri = getUri(String.format(HostConstants.HOST_URL + HostConstants.DELETE_CATEGORY_URL, request.getCategoryId()));
        String jsonRes = HttpClientManager.getInstance().execPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
        LogUtils.log(String.format("create response: %s", jsonRes));
        if (StringUtils.isNotEmpty(jsonRes)) {
            response = JsonUtils.fromJsonString(jsonRes, BaseResponse.class);
        }
        return response;
    }

    /**
     * 获取视频分类
     * @param request 请求的实体对象
     * @return VodCategoryListData
     */
    public VodCategoryListData getCategoryList(VodCategoryListRequset request){
        LogUtils.log(String.format("create request: %s", JsonUtils.toJsonString(request)));
        VodCategoryListData vodCategoryListData = null;
        setHeader();
        URI uri = getUri(HostConstants.HOST_URL + HostConstants.CATEGORY_LIST_URL);
        String jsonRes = HttpClientManager.getInstance().execPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
        LogUtils.log(String.format("create response: %s", jsonRes));
        if (StringUtils.isNotEmpty(jsonRes)) {
            vodCategoryListData = JsonUtils.fromJsonString(jsonRes, VodCategoryListData.class);
        }
        return vodCategoryListData;
    }

    /**
     * 上传视频
     * @param request 请求的实体对象
     * @return VodChannelUploadResponse
     */
    public VodChannelUploadResponse uploadChannel(VodChannelUploadRequest request){
        LogUtils.log(String.format("create request: %s", JsonUtils.toJsonString(request)));

        VodChannelUploadResponse response = null;
        setHeader();
        URI uri = getUri(HostConstants.HOST_URL + HostConstants.UPLODAD_CHANNEL_URL);
        String jsonRes = HttpClientManager.getInstance().execPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
        LogUtils.log(String.format("create response: %s", jsonRes));
        if (StringUtils.isNotEmpty(jsonRes)) {
            VodChannelUploadDate vodChannelUploadDate = JsonUtils.fromJsonString(jsonRes, VodChannelUploadDate.class);
            if ("0".equals(vodChannelUploadDate.getErr())) {
                response = vodChannelUploadDate.getData();
            } else {
                response = new VodChannelUploadResponse();
            }
            response.setErr(vodChannelUploadDate.getErr());
            response.setMsg(vodChannelUploadDate.getMsg());
        }
        return response;
    }

    /**
     * 获取视频信息
     * @param request 请求的实体对象
     * @return VodChannelInfoResponse
     */
    public VodChannelInfoResponse getChannelInfo(VodChannalInfoRequest request){
        LogUtils.log(String.format("create request: %s", JsonUtils.toJsonString(request)));

        VodChannelInfoResponse response = null;
        setHeader();
        URI uri = getUri(String.format(HostConstants.HOST_URL + HostConstants.CHANNEL_INFO_URL, request.getChannelWebId()));
        String jsonRes = HttpClientManager.getInstance().execGetRequestWithHeader(uri.toString(), headerMap, proxyConfig);
        LogUtils.log(String.format("create response: %s", jsonRes));
        if (StringUtils.isNotEmpty(jsonRes)) {
            VodChannelInfoData vodChannelInfoData = JsonUtils.fromJsonString(jsonRes, VodChannelInfoData.class);
            if ("0".equals(vodChannelInfoData.getErr())) {
                response = vodChannelInfoData.getData();
            } else {
                response = new VodChannelInfoResponse();
            }
            response.setErr(vodChannelInfoData.getErr());
            response.setMsg(vodChannelInfoData.getMsg());
        }
        return response;
    }

    /**
     * 获取视频列表
     * @param request 请求的实体对象
     * @return VodChannelListResponse
     */
    public VodChannelListResponse getChannelList(VodChannelListRequest request){
        LogUtils.log(String.format("create request: %s", JsonUtils.toJsonString(request)));
        VodChannelListResponse response = null;
        setHeader();
        URI uri = getUri(HostConstants.HOST_URL + HostConstants.CHANNEL_LIST_URL);
        String jsonRes = HttpClientManager.getInstance().execPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
        LogUtils.log(String.format("create response: %s", jsonRes));
        if (StringUtils.isNotEmpty(jsonRes)) {
            response = JsonUtils.fromJsonString(jsonRes, VodChannelListResponse.class);
        }
        return response;
    }

    /**
     * 上传视频
     * @param request 请求的实体对象
     * @return VodChannelCommonResponse
     */
    public VodChannelCommonResponse updateChannel(VodChannelUpdateRequest request){

        LogUtils.log(String.format("create request: %s", JsonUtils.toJsonString(request)));

        VodChannelCommonResponse response = null;
        setHeader();
        URI uri = getUri(String.format(HostConstants.HOST_URL + HostConstants.UPDATE_CHANNEL_URL,request.getChannelWebId()));
        String jsonRes = HttpClientManager.getInstance().execPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
        LogUtils.log(String.format("create response: %s", jsonRes));
        if (StringUtils.isNotEmpty(jsonRes)) {
            response = JsonUtils.fromJsonString(jsonRes, VodChannelCommonResponse.class);
        }
        return response;
    }

    /**
     * 设置是否屏蔽视频
     * @param request 请求的实体对象
     * @return VodChannelCommonResponse
     */
    public VodChannelCommonResponse setChannelPlayable(VodChannalPlayableRequest request){

        LogUtils.log(String.format("create request: %s", JsonUtils.toJsonString(request)));

        VodChannelCommonResponse response = null;
        setHeader();
        URI uri = getUri(HostConstants.HOST_URL + HostConstants.SET_CHANNEL_PLAYABLE_URL);
        String jsonRes = HttpClientManager.getInstance().execPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
        LogUtils.log(String.format("create response: %s", jsonRes));
        if (StringUtils.isNotEmpty(jsonRes)) {
            response = JsonUtils.fromJsonString(jsonRes, VodChannelCommonResponse.class);
        }
        return response;
    }

    /**
     * 删除视频
     * @param request 请求的实体对象
     * @return VodChannelCommonResponse
     */
    public VodChannelCommonResponse deleteChannel(VodChannelDeleteRequest request){

        LogUtils.log(String.format("create request: %s", JsonUtils.toJsonString(request)));

        VodChannelCommonResponse response = null;
        setHeader();
        URI uri = getUri(HostConstants.HOST_URL + HostConstants.DELETE_CHANNEL_URL);
        String jsonRes = HttpClientManager.getInstance().execPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
        LogUtils.log(String.format("create response: %s", jsonRes));
        if (StringUtils.isNotEmpty(jsonRes)) {
            response = JsonUtils.fromJsonString(jsonRes, VodChannelCommonResponse.class);
        }
        return response;
    }

    private void setHeader() {
        headerMap.put("Authorization", AccessTokenSigner.getInstance().getAccessToken());
    }

    private void setHeader(Integer expireInMinutes) {
        headerMap.put("Authorization", AccessTokenSigner.getInstance().getAccessToken(expireInMinutes));
    }

    private URI getUri(String url) {
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            // 不向外抛出该异常增加外部处理的麻烦，url地址都由内部组装可保证格式正确性
            return null;
        }
    }
}
