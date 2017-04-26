package com.pplive.ppcloud;

import com.pplive.ppcloud.auth.AccessTokenSigner;
import com.pplive.ppcloud.auth.AuthCredentials;
import com.pplive.ppcloud.http.HttpProxyConfig;
import com.pplive.ppcloud.quick.VodCategoryManager;
import com.pplive.ppcloud.quick.VodChannelMannager;
import com.pplive.ppcloud.quick.model.VodCategoryInfoModel;
import com.pplive.ppcloud.quick.model.VodChannelInfoModel;
import com.pplive.ppcloud.request.VodChannelCreateRequest;
import com.pplive.ppcloud.response.VodChannelUploadResponse;
import com.pplive.ppcloud.vod.VodManager;

import java.util.List;
import java.util.Set;

/**
 * Created by robertpicyu on 2016/11/28.
 * @author ylc
 */
public class VodClient {
    /**
     * 初始化客户端,设置accessKey，secretKey
     * @param accessKey accessKey
     * @param secretKey secretKey
     */
    public VodClient(String accessKey, String secretKey) {
        AuthCredentials authCredentials = new AuthCredentials();
        authCredentials.setAccessKey(accessKey);
        authCredentials.setSecretKey(secretKey);
        AccessTokenSigner.getInstance().setAuthCredentials(authCredentials);
    }

    public VodClient(){

    }

    public void setProxyConfig(String proxyHost,int proxyPort) {
        HttpProxyConfig proxyConfig = new HttpProxyConfig(proxyHost,proxyPort);
        VodManager.getInstance().setProxyConfig(proxyConfig);
    }

    public void cleanProxyConfig(){
        VodManager.getInstance().setProxyConfig(null);
    }
    /**
     * 添加分类
     * @param categoryName ：分类的名称
     * @return VodCategoryInfoModel ：分类信息
     */
    public VodCategoryInfoModel addCategory(String categoryName){
        return VodCategoryManager.getInstance().addCategory(categoryName);
    }

    /**
     * 删除分类
     * @param categoryId  ： 要删除视频类的Id
     * @return 无
     */
    public void deleteCategory(Long categoryId){
        VodCategoryManager.getInstance().deleteCategory(categoryId);
    }

    /**
     * 获取单个视频分类的信息
     * @param id ：视频分类的id
     * @return VodCategoryInfoModel
     */
    public VodCategoryInfoModel getVodCategoryById(Long id){
        return VodCategoryManager.getInstance().getVodCategory(id);
    }

    /**
     * 获取单个视频分类的信息
     * @param categoryName 视频分类的名称
     * @return  同名的所有视频分类
     */
    public List<VodCategoryInfoModel> getVodCategoryByName(String categoryName){
        return VodCategoryManager.getInstance().getVodCategorys(categoryName);
    }



    /**
     * 获取视频分类列表
     * @return VodCategoryInfoModel
     */
    public  VodCategoryInfoModel[] getVodCategoryList(Integer needCount){
        return VodCategoryManager.getInstance().getVodCategoryList(needCount);
    }

    /**
     * 获取视频分类下所有视频的详细信息
     * @param categoryId    ：分类Id
     * @param pageNum       ：分页号
     * @param pageSize      ：分页大小
     * @return VodChannelInfoModel[] 视频信息列表
     */
    public VodChannelInfoModel[] getVodChannelList(Long categoryId, Integer pageNum, Integer pageSize){
        return VodCategoryManager.getInstance().getVodChannelList(categoryId,pageNum,pageSize);
    }

    /**
     * 获取视频分类下所有视频的详细信息
     * @param categoryId    ：分类Id
     * @return VodChannelInfoModel[] 视频信息列表
     */
    public VodChannelInfoModel[] getVodChannelList(Long categoryId){
        return VodCategoryManager.getInstance().getVodChannelList(categoryId);
    }

    /**
     * 更新分类信息
     * @param categoryId ：视频分类id号
     * @param finalName : 修改后的名称
     */
    public void updateCategory(Long categoryId, String finalName){
        VodCategoryManager.getInstance().updateCategory(categoryId,finalName);
    }

    /**
     * 创建点播
     * @param filePath : 点播文件路径
     * @param request: 创建点播的信息
     *         request.getName()    : 点播名称，非空
     *   其它可设置信息：
     *         request.getCoverImg() : 封面地址
     *         request.getSummary() ： 视频文件简介
     *         request.getCategoryId() : 点播保存到哪个分类下（为空保存到默认分类）
     * @return
     *         VodChannelCreateResponse ：PP云点播信息，其中包含upToken
     */
    public VodChannelUploadResponse createVod(String filePath, VodChannelCreateRequest request){
        return VodManager.getInstance().createVod(filePath, request);
    }

    /**
     * 删除点播视频
     * @param channelWebids ：要删除的点播视频web id集合
     */
    public void deleteVodChannel(String... channelWebids){
        VodChannelMannager.getInstance().deleteVodChannel(channelWebids);
    }

    /**
     * 获取点播视频的详细信息
     * @param channelWebId : 视频webId
     * @return 点播视频信息
     */
    public VodChannelInfoModel getVodChannelInfo(String channelWebId){
        return VodChannelMannager.getInstance().getVodChannelInfo(channelWebId);
    }

    /**
     * 设置 点播不可播放
     * @param channelWebids ：视频webid集合
     */
    public void enableVodChannel(String... channelWebids){
        VodChannelMannager.getInstance().enableVodChannel(channelWebids);
    }

    /**
     * 设置 点播可播放
     * @param channelWebids ：视频webid集合
     */
    public void disableVodChannel(String... channelWebids){
        VodChannelMannager.getInstance().disableVodChannel(channelWebids);
    }

    /**
     *  更新点播视频名称
     * @param channelWebId  ：待更新点播的weiId
     * @param channelName   :点播修改后的名称
     * @return VodChannelInfoModel
     */
    public VodChannelInfoModel updateVodChannelName(String channelWebId, String channelName){
        return VodChannelMannager.getInstance().updateVodChannelName(channelWebId,channelName);
    }

    /**
     *  更新点播视频介绍
     * @param channelWebId  ：待更新点播的weiId
     * @param channelSummary：点播介绍
     * @return VodChannelInfoModel
     */
    public VodChannelInfoModel updateVodChannelSummary(String channelWebId, String channelSummary){
        return VodChannelMannager.getInstance().updateVodChannelSummary(channelWebId,channelSummary);
    }

    /**
     *  更新点播视频封面
     * @param channelWebId  ：待更新点播的weiId
     * @param coverImage    ：封面地址
     * @return VodChannelInfoModel
     */
    public VodChannelInfoModel updateVodChannelCoverImage(String channelWebId, String coverImage){
        return VodChannelMannager.getInstance().updateVodChannelCoverImage(channelWebId,coverImage);
    }
}
