package com.pplive.ppcloud.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by robertpicyu on 2016/12/1.
 */
public class VodCategoryAddResponse extends BaseResponse{
    /**
     * 视频类id
     */
    private Long id;

    @JsonProperty("category_name")
    private String categoryName;

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
}
