package com.pplive.ppcloud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by robertpicyu on 2016/12/2.
 */
public class VodChannelCreateRequest {
    /**
     *  用户分类id号
     */
    @JsonProperty("category_id")
    private Integer categoryId;

    /**
     * 视频名称
     */
    private String name;

    /**
     * 视频简介
     */
    private String summary;
    /**
     * 封面图url地址
     */
    @JsonProperty("cover_img")
    private String  coverImg;
    /**
     * 视频文件大小
     */
    private Long length;
    /**
     * 视频特征码
     */
    private String ppfeature;
    /**
     * 响应中返回 up_token 的标志位
     */
    private String getuptk;
    /**
     * ppfeature 重用表示，channel未上传完成才新建
     */
    private String reuse;

    /**
     * 短视频标志
     * 0：非短视频
     * 1：短视频
     */
    @JsonProperty("skip_encode")
    private Integer skipEncode = 0;

    /**
     * 视频宽度
     */
    private Integer width;

    /**
     * 视频高度
     */
    private Integer height;

    /**
     * 码流率
     */
    private Integer bitrate;

    /**
     * 帧率
     */
    private Integer framerate;

    /**
     * 视频时长
     */
    private Integer duration;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getPpfeature() {
        return ppfeature;
    }

    public void setPpfeature(String ppfeature) {
        this.ppfeature = ppfeature;
    }

    public String getGetuptk() {
        return getuptk;
    }

    public void setGetuptk(String getuptk) {
        this.getuptk = getuptk;
    }

    public String getReuse() {
        return reuse;
    }

    public void setReuse(String reuse) {
        this.reuse = reuse;
    }

    public Integer getSkipEncode() {
        return skipEncode;
    }

    public void setSkipEncode(Integer skipEncode) {
        this.skipEncode = skipEncode;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getBitrate() {
        return bitrate;
    }

    public void setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
    }

    public Integer getFramerate() {
        return framerate;
    }

    public void setFramerate(Integer framerate) {
        this.framerate = framerate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
