package com.pplive.ppcloud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by robertpicyu on 2016/12/5.
 */
public class VodChannalPlayableRequest {
    /**
     *  1:开启;0:关闭
     */
    @JsonProperty("is_play")
    private Integer isPlay;

    /**
     *  视频webid集合(逗号分隔)
     */
    @JsonProperty("channel_web_ids")
    private String channelWebIds;

    public Integer getIsPlay() {
        return isPlay;
    }

    public void setIsPlay(Integer isPlay) {
        this.isPlay = isPlay;
    }

    public String getChannelWebIds() {
        return channelWebIds;
    }

    public void setChannelWebIds(String channelWebIds) {
        this.channelWebIds = channelWebIds;
    }
}
