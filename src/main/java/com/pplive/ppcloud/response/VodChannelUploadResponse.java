package com.pplive.ppcloud.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by robertpicyu on 2016/12/2.
 */
public class VodChannelUploadResponse extends BaseResponse{
    private Integer id;
    /**
     * 文件大小
     */
    private Integer length;
    /**
     * 视频特征码
     */
    private String ppfeature;
    /**
     * 视频持续时长
     */
    private Integer duration;
    /**
     * 截图
     */
    private String screenshot;
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
     * 子账户id
     */
    @JsonProperty("sub_user_id")
    private Integer subuserId;
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
     *文件id
     */
    private Integer fid;
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
     *文件状态
     */
    @JsonProperty("file_status")
    private String fileStatus;
    /**
     * 文件上传凭证
     */
    @JsonProperty("up_token")
    private String upToken;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getPpfeature() {
        return ppfeature;
    }

    public void setPpfeature(String ppfeature) {
        this.ppfeature = ppfeature;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
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

    public Integer getSubuserId() {
        return subuserId;
    }

    public void setSubuserId(Integer subuserId) {
        this.subuserId = subuserId;
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

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
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

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getUpToken() {
        return upToken;
    }

    public void setUpToken(String upToken) {
        this.upToken = upToken;
    }
}
