package com.pplive.ppcloud.vod;

import com.pplive.ppcloud.constant.HostConstants;
import com.pplive.ppcloud.constant.VersionConstants;
import com.pplive.ppcloud.auth.AccessTokenSigner;
import com.pplive.ppcloud.exception.InvalidRuntimeException;
import com.pplive.ppcloud.http.HttpClientManager;
import com.pplive.ppcloud.http.HttpProxyConfig;
import com.pplive.ppcloud.request.*;
import com.pplive.ppcloud.response.*;
import com.pplive.ppcloud.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by robertpicyu on 2016/12/1.
 */
public class VodManager {

    private VodManager(){}
    private static final Logger logger = LoggerFactory.getLogger(VodManager.class);

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
        String jsonRes = HttpClientManager.getInstance().execJsonPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
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
        String jsonRes = HttpClientManager.getInstance().execJsonPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
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
        String jsonRes = HttpClientManager.getInstance().execJsonPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
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
        String jsonRes = HttpClientManager.getInstance().execJsonPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
        LogUtils.log(String.format("create response: %s", jsonRes));
        if (StringUtils.isNotEmpty(jsonRes)) {
            vodCategoryListData = JsonUtils.fromJsonString(jsonRes, VodCategoryListData.class);
        }
        return vodCategoryListData;
    }

    /**
     * 创建点播（在PP云生成点播相关信息）
     * @param filePath : 点播文件路径
     * @param request: 创建点播的信息
     *         request.getName()    : 点播名称，非空
     *   其它可设置信息：
     *         request.getCoverImg() : 封面地址
     *         request.getSummary() ： 视频文件简介
     * @return
     *         VodChannelUploadResponse ：PP云点播信息
     */
    public VodChannelUploadResponse createVod(String filePath, VodChannelCreateRequest request){
        try {
            String ppfeature = FeathureUtil.getPPFeature(filePath);
            Long  fileSize = Files.size(Paths.get(filePath));

            /* upload vod metedata */
            request.setPpfeature(ppfeature);
            request.setLength(fileSize);

            return createVod(request);
        }catch (Exception e){
            logger.error("create vod failed!" + e.getCause());
            throw new InvalidRuntimeException("" + e.getCause());
        }
    }


    /**
     * 创建点播（在PP云生成点播相关信息）
     * @param request: 创建点播的信息
     *         request.getName()    : 点播名称，非空
     *         request.getPpfeature() ：特征码，非空
     *         request.getLength() ：点播文件长度，非空
     *   其它可设置信息：
     *         request.getCoverImg() : 封面地址
     *         request.getSummary() ： 视频文件简介
     * @return
     *         VodChannelUploadResponse ：PP云点播信息
     */
    public VodChannelUploadResponse createVod(VodChannelCreateRequest request){
        try {
            /* upload vod metedata */
            if ( StringUtils.isEmpty(request.getName()) || StringUtils.isEmpty(request.getPpfeature()) ||
                    request.getLength() == null){
                throw new InvalidRuntimeException("creat vod need name ppfeature and size");
            }
            request.setGetuptk("1");
            request.setReuse("1");
            VodChannelUploadResponse vodChannelUploadResponse = addChannelInfo(request);
            if (null == vodChannelUploadResponse || !vodChannelUploadResponse.getErr().equals(ResultBean.OK.toString())) {
                throw new InvalidRuntimeException("create channel failed");
            }
            logger.info("create vod successfully!\nresult:{}",JsonUtils.toJsonString(vodChannelUploadResponse));
            return vodChannelUploadResponse;
        }catch (Exception e){
            logger.error("create vod failed!" + e.getCause());
            throw new InvalidRuntimeException("create channel failed");
        }
    }

    /**
     * 创建点播：在PPyun上创建点播并获取点播的下载地址
     * @param request 请求的实体对象
     * @return VodChannelUploadResponse
     */
    private VodChannelUploadResponse addChannelInfo(VodChannelCreateRequest request){
        logger.info(String.format("create request: %s", JsonUtils.toJsonString(request)));

        VodChannelUploadResponse response = null;
        setHeader();
        URI uri = getUri(HostConstants.HOST_URL + HostConstants.UPLODAD_CHANNEL_URL);
        String jsonRes = HttpClientManager.getInstance().execPostRequest(uri, headerMap, request, proxyConfig);
        logger.info(String.format("create response: %s", jsonRes));
        if (StringUtils.isNotEmpty(jsonRes)) {
            VodChannelUploadDate vodChannelUploadDate = JsonUtils.fromJsonString(jsonRes, VodChannelUploadDate.class);
            if (!vodChannelUploadDate.getErr().equals("0")) {
                throw new InvalidRuntimeException("addChannelInfo failed! " + vodChannelUploadDate.getMsg());
            }
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
        String jsonRes = HttpClientManager.getInstance().execJsonPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
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
        String jsonRes = HttpClientManager.getInstance().execJsonPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
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
        String jsonRes = HttpClientManager.getInstance().execJsonPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
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
        String jsonRes = HttpClientManager.getInstance().execJsonPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
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
