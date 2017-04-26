package com.pplive.ppcloud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by robertpicyu on 2016/12/5.
 */
public class VodChannelListRequest {
    /**
     *  用户分类id号
     */
    @JsonProperty("category_id")
    private Long categoryId;
    /**
     *  视频类型：1，点播；2：直播
     */
    @JsonProperty("channel_type")
    private String channelType;
    /**
     *  是否跳过压制：1，跳过（即短视频）；0，不跳过（默认）
     */
    @JsonProperty("skip_encode")
    private Integer skipEncode;
    /**
     *  视频webid
     */
    @JsonProperty("channel_web_id")
    private String channelWebId;

    /**
     * 视频搜索关键字
     */
    private String key;
    /**
     * 直播状态码
     */
    @JsonProperty("live_status")
    private String  liveStatus;
    /**
     * 视频特征码
     */
    private String ppfeature;
    /**
     * 当前页
     */
    @JsonProperty("page_num")
    private Integer pageNum;
    /**
     * 每页记录数
     */
    @JsonProperty("page_size")
    private Integer pageSize;
    /**
     * 创建开始时间
     */
    @JsonProperty("create_time_start")
    private String  createTimeStart;
    /**
     * 创建结束时间
     */
    @JsonProperty("create_time_end")
    private String  createTimeEnd;
    /**
     * 过滤直转点失败视频标志：
     *  缺省：不过滤任何视频
     *  0   ：过滤直转点失败视频
     *  1   ： 过滤正常视频
     */
    private String  abnormalLive;

    public Integer getSkipEncode() {
        return skipEncode;
    }

    public void setSkipEncode(Integer skipEncode) {
        this.skipEncode = skipEncode;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getChannelWebId() {
        return channelWebId;
    }

    public void setChannelWebId(String channelWebId) {
        this.channelWebId = channelWebId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(String liveStatus) {
        this.liveStatus = liveStatus;
    }

    public String getPpfeature() {
        return ppfeature;
    }

    public void setPpfeature(String ppfeature) {
        this.ppfeature = ppfeature;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getAbnormalLive() {
        return abnormalLive;
    }

    public void setAbnormalLive(String abnormalLive) {
        this.abnormalLive = abnormalLive;
    }
}
