package com.pplive.ppcloud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by robertpicyu on 2016/12/5.
 */
public class VodChannelDeleteRequest {
    /**
     *  视频webid集合(逗号分隔)
     */
    @JsonProperty("channel_web_ids")
    private String channelWebIds;

    public String getChannelWebIds() {
        return channelWebIds;
    }

    public void setChannelWebIds(String channelWebIds) {
        this.channelWebIds = channelWebIds;
    }
}
