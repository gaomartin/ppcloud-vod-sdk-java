package com.pplive.ppcloud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by robertpicyu on 2016/12/5.
 */
public class VodChannelUpdateRequest {
    /**
     *  视频webid
     */
    @JsonProperty("channel_web_id")
    private String channelWebId;
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
    @JsonProperty("cover_img")
    private String coverImg;

    public String getChannelWebId() {
        return channelWebId;
    }

    public void setChannelWebId(String channelWebId) {
        this.channelWebId = channelWebId;
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

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }
}
