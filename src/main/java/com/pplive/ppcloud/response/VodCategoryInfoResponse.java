package com.pplive.ppcloud.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by robertpicyu on 2016/12/6.
 */
public class VodCategoryInfoResponse {
    /**
     * 分类的id
     */
    private Long id;
    /**
     * 分类名称
     */
    @JsonProperty("category_name")
    private  String categoryName;
    /**
     * 创建时间
     */
    @JsonProperty("create_time")
    private String createTime;
    /**
     * 用户id
     */
    @JsonProperty("user_id")
    private Integer userId;
    /**
     * 分类下的通道数量
     */
    @JsonProperty("channel_num")
    private Integer channelNum;

    public Integer getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(Integer channelNum) {
        this.channelNum = channelNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}
