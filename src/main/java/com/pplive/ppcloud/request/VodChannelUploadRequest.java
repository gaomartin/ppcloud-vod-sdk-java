package com.pplive.ppcloud.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.xpath.internal.operations.String;

/**
 * Created by robertpicyu on 2016/12/2.
 */
public class VodChannelUploadRequest {
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
    private Integer length;
    /**
     * 视频特征码
     */
    private String ppfeature;
    /**
     * 响应中返回 up_token 的标志位
     */
    private Integer getuptk;
    /**
     * ppfeature 重用表示，channel未上传完成才新建
     */
    private Integer reuse;

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

    public Integer getGetuptk() {
        return getuptk;
    }

    public void setGetuptk(Integer getuptk) {
        this.getuptk = getuptk;
    }

    public Integer getReuse() {
        return reuse;
    }

    public void setReuse(Integer reuse) {
        this.reuse = reuse;
    }
}
