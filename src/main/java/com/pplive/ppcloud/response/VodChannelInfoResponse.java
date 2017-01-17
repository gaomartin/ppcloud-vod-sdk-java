package com.pplive.ppcloud.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by robertpicyu on 2016/12/2.
 */
public class VodChannelInfoResponse extends BaseResponse {
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
    @JsonProperty("is_play")
    private Integer isPlay;
    /**
     * 分类ID
     */
    @JsonProperty("category_id")
    private Integer categoryId;
    /**
     * 用户ID
     */
    @JsonProperty("user_id")
    private Integer userId;
    /**
     * 视频名称
     */
    @JsonProperty("channel_name")
    private String channelName;
    /**
     *视频描述
     */
    @JsonProperty("channel_summary")
    private String channelSummary;
    /**
     *封面图片
     */
    @JsonProperty("cover_image")
    private String coverImage;
    /**
     *视频id
     */
    @JsonProperty("channel_id")
    private Integer channelId;
    /**
     *创建时间
     */
    @JsonProperty("create_time")
    private String createTime;
    /**
     * 视频webid
     */
    @JsonProperty("channel_web_id")
    private String channelWebId;
    /**
     * 视频类型
     */
    @JsonProperty("channel_type")
    private Integer channelType;
    /**
     * 转码状态
     */
    @JsonProperty("transcode_status")
    private Integer transcodeStatus;
    /**
     * 直播状态
     */
    @JsonProperty("live_status")
    private String liveStatus;
    /**
     * 时长
     */
    @JsonProperty("real_duration")
    private Integer realDuration;
    /**
     * 截图
     */
    @JsonProperty("screen_shot")
    private String screenshot;
    /**
     * 直播开始时间
     */
    @JsonProperty("live_start_time")
    private Long liveStartTime;
    /**
     * 直播结束时间
     */
    @JsonProperty("live_end_time")
    private Long liveEndTime;
    /**
     * 直播类型(1:限时直播;0:24小时直播)
     */
    @JsonProperty("time_limit")
    private Integer timeLimit;
    /**
     * 直转点状态表
     */
    @JsonProperty("live_vod_status")
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
}
