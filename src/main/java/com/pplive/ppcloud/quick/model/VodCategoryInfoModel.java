package com.pplive.ppcloud.quick.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pplive.ppcloud.response.VodCategoryInfoResponse;

/**
 * Created by robertpicyu on 2016/12/5.
 */
public class VodCategoryInfoModel {
    /**
     * 分类的id
     */
    private Long id;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 错误代码
     */
    @JsonIgnore
    public String err = "0";

    /**
     * 错误信息
     */
    @JsonIgnore
    public String msg;

    /**
     * 分类下的通道数量
     */
    private Integer channelNum;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

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


    public void setMsg(String msg) {
        this.msg = msg;
    }

    public VodCategoryInfoModel(VodCategoryInfoResponse vodCategoryInfoResponse) {
        id = vodCategoryInfoResponse.getId();
        userId = vodCategoryInfoResponse.getUserId();
        categoryName = vodCategoryInfoResponse.getCategoryName();
        createTime = vodCategoryInfoResponse.getCreateTime();
        channelNum = vodCategoryInfoResponse.getChannelNum();
    }

    public VodCategoryInfoModel() {
    }
}
