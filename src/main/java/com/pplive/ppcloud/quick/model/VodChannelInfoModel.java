package com.pplive.ppcloud.quick.model;

import com.pplive.ppcloud.response.VodChannelInfoResponse;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by robertpicyu on 2016/12/6.
 */
public class VodChannelInfoModel {
    private Integer id;
    /**
     * 视频持续时长
     */
    private Integer duration;
    /**
     * 有效观看次数
     */
    private Integer vv;
    /**
     * 是否可播放
     */
    private Integer isPlay;
    /**
     * 分类ID
     */
    private Integer categoryId;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 视频名称
     */
    private String channelName;
    /**
     *视频描述
     */
    private String channelSummary;
    /**
     *封面图片
     */
    private String coverImage;
    /**
     *视频id
     */
    private Integer channelId;
    /**
     *创建时间
     */
    private String createTime;
    /**
     * 视频webid
     */
    private String channelWebId;
    /**
     * 视频类型
     */
    private Integer channelType;
    /**
     * 转码状态
     */
    private Integer transcodeStatus;
    /**
     * 直播状态
     */
    private String liveStatus;
    /**
     * 时长
     */
    private Integer realDuration;
    /**
     * 截图
     */
    private String screenshot;
    /**
     * 直播开始时间
     */
    private Long liveStartTime;
    /**
     * 直播结束时间
     */
    private Long liveEndTime;
    /**
     * 直播类型(1:限时直播;0:24小时直播)
     */
    private Integer timeLimit;
    /**
     * 直转点状态表
     */
    private String liveVodStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getVv() {
        return vv;
    }

    public void setVv(Integer vv) {
        this.vv = vv;
    }

    public Integer getIsPlay() {
        return isPlay;
    }

    public void setIsPlay(Integer isPlay) {
        this.isPlay = isPlay;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelSummary() {
        return channelSummary;
    }

    public void setChannelSummary(String channelSummary) {
        this.channelSummary = channelSummary;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getChannelWebId() {
        return channelWebId;
    }

    public void setChannelWebId(String channelWebId) {
        this.channelWebId = channelWebId;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public Integer getTranscodeStatus() {
        return transcodeStatus;
    }

    public void setTranscodeStatus(Integer transcodeStatus) {
        this.transcodeStatus = transcodeStatus;
    }

    public String getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(String liveStatus) {
        this.liveStatus = liveStatus;
    }

    public Integer getRealDuration() {
        return realDuration;
    }

    public void setRealDuration(Integer realDuration) {
        this.realDuration = realDuration;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public Long getLiveStartTime() {
        return liveStartTime;
    }

    public void setLiveStartTime(Long liveStartTime) {
        this.liveStartTime = liveStartTime;
    }

    public Long getLiveEndTime() {
        return liveEndTime;
    }

    public void setLiveEndTime(Long liveEndTime) {
        this.liveEndTime = liveEndTime;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getLiveVodStatus() {
        return liveVodStatus;
    }

    public void setLiveVodStatus(String liveVodStatus) {
        this.liveVodStatus = liveVodStatus;
    }

    public VodChannelInfoModel(VodChannelInfoResponse response) {
        userId=response.getUserId();
        categoryId = response.getCategoryId();
        channelId = response.getChannelId();
        channelWebId = response.getChannelWebId();
        channelName = response.getChannelName();
        channelSummary = response.getChannelSummary();
        channelType = response.getChannelType();
        coverImage = response.getCoverImage();
        duration = response.getDuration();
        realDuration = response.getRealDuration();
        screenshot = response.getScreenshot();
        createTime = response.getCreateTime();
        id = response.getId();
        vv = response.getVv();
        transcodeStatus = response.getTranscodeStatus();
        timeLimit = response.getTimeLimit();
        liveStatus = response.getLiveVodStatus();
        liveEndTime = response.getLiveEndTime();
        liveStartTime = response.getLiveStartTime();
        liveVodStatus = response.getLiveVodStatus();
        liveStatus = response.getLiveStatus();
        isPlay = response.getIsPlay();
    }

    public VodChannelInfoModel() {
    }

    public static VodChannelInfoModel[] getVodChannelList(VodChannelInfoResponse[] responses){
        List<VodChannelInfoModel> vodChannelInfos = new LinkedList<VodChannelInfoModel>();

        for (int i = 0; i < responses.length; i++) {
            vodChannelInfos.add(new VodChannelInfoModel(responses[i]));
        }

        return (VodChannelInfoModel[]) vodChannelInfos.toArray(new VodChannelInfoModel[0]);
    }
}
