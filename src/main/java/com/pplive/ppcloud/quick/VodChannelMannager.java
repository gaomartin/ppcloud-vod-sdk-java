package com.pplive.ppcloud.quick;

import com.pplive.ppcloud.vod.VodManager;
import com.pplive.ppcloud.quick.model.VodChannelInfoModel;
import com.pplive.ppcloud.request.*;
import com.pplive.ppcloud.response.VodChannelCommonResponse;
import com.pplive.ppcloud.response.VodChannelInfoResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by robertpicyu on 2016/12/5.
 */
public class VodChannelMannager {
    private VodChannelMannager(){}

    public static VodChannelMannager getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static VodChannelMannager instance = new VodChannelMannager();
    }


    /**
     * 获取点播视频的详细信息
     * @param channelWebId : 视频webId
     * @return VodChannelInfoModel 点播视频信息
     */
    public VodChannelInfoModel getVodChannelInfo(String channelWebId){
        VodChannalInfoRequest request = new VodChannalInfoRequest();
        VodChannelInfoResponse response = null;
        request.setChannelWebId(channelWebId);

        response = VodManager.getInstance().getChannelInfo(request);
        if (response == null || !response.getErr().equals("0")){
            throw new RuntimeException("get vod info fail!");
        }
        return new VodChannelInfoModel(response);
    }



    /**
     *  更新点播视频名称
     * @param channelWebId  ：待更新点播的weiId
     * @param channelName   :点播修改后的名称
     * @return VodChannelInfoModel
     */
    public VodChannelInfoModel updateVodChannelName(String channelWebId, String channelName){
        try {
            VodChannelUpdateRequest request = new VodChannelUpdateRequest();
            VodChannelCommonResponse response = null;

            request.setChannelWebId(channelWebId);
            request.setChannelName(URLEncoder.encode(channelName,"UTF-8"));
            response = VodManager.getInstance().updateChannel(request);

            if (response == null || !response.getErr().equals("0")){
                throw new RuntimeException("update vod fail!");
            }

            return  getVodChannelInfo(channelWebId);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     *  更新点播视频介绍
     * @param channelWebId  ：待更新点播的weiId
     * @param channelSummary：点播介绍
     * @return VodChannelInfoModel
     */
    public VodChannelInfoModel updateVodChannelSummary(String channelWebId, String channelSummary){
        try {
            VodChannelUpdateRequest request = new VodChannelUpdateRequest();
            VodChannelCommonResponse response = null;

            request.setChannelWebId(channelWebId);
            request.setChannelSummary(URLEncoder.encode(channelSummary,"UTF-8"));
            response = VodManager.getInstance().updateChannel(request);

            if (response == null || !response.getErr().equals("0")){
                throw new RuntimeException("update vod fail!");
            }

            return  getVodChannelInfo(channelWebId);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     *  更新点播视频封面
     * @param channelWebId  ：待更新点播的weiId
     * @param coverImage    ：封面地址
     * @return VodChannelInfoModel
     */
    public VodChannelInfoModel updateVodChannelCoverImage(String channelWebId, String coverImage){
            VodChannelUpdateRequest request = new VodChannelUpdateRequest();

            VodChannelCommonResponse response = null;
            request.setChannelWebId(channelWebId);
            request.setCoverImg(coverImage);
            response = VodManager.getInstance().updateChannel(request);
            if (response == null || !response.getErr().equals("0")){
                throw new RuntimeException("update vod fail!");
            }
            return  getVodChannelInfo(channelWebId);
    }

    /**
     * 删除点播视频
     * @param channelWebids ：要删除的点播视频web id集合
     */
    public void deleteVodChannel(String... channelWebids){
        VodChannelDeleteRequest request = new VodChannelDeleteRequest();
        VodChannelCommonResponse response = null;

        StringBuffer buffer = new StringBuffer();
        for (String channelWebId : channelWebids) {
            buffer.append(channelWebId + ";");
        }
        request.setChannelWebIds(buffer.toString());
        response = VodManager.getInstance().deleteChannel(request);

        if (response == null || !response.getErr().equals("0")){
            throw new RuntimeException("delete channel failed！");
        }
    }

    /**
     * 设置 点播不可播放
     * @param channelWebids ：视频webid集合
     */
    public void enableVodChannel(String... channelWebids){
        VodChannalPlayableRequest request = new VodChannalPlayableRequest();
        VodChannelCommonResponse response = null;

        StringBuffer buffer = new StringBuffer();
        for (String channelWebId : channelWebids) {
            buffer.append(channelWebId + ";");
        }
        request.setChannelWebIds(buffer.toString());
        request.setIsPlay(1);

        response = VodManager.getInstance().setChannelPlayable(request);
        if (response == null || !response.getErr().equals("0")){
            throw new RuntimeException("set channel playable failed！");
        }
    }

    /**
     * 设置 点播可播放
     * @param channelWebids ：视频webid集合
     */
    public void disableVodChannel(String... channelWebids){
        VodChannalPlayableRequest request = new VodChannalPlayableRequest();
        VodChannelCommonResponse response = null;

        StringBuffer buffer = new StringBuffer();
        for (String channelWebId : channelWebids) {
            buffer.append(channelWebId + ";");
        }
        request.setChannelWebIds(buffer.toString());
        request.setIsPlay(0);

        response = VodManager.getInstance().setChannelPlayable(request);
        if (response == null || !response.getErr().equals("0")){
            throw new RuntimeException("set channel playable failed！");
        }
    }
}
