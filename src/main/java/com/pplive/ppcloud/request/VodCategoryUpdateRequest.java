package com.pplive.ppcloud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by robertpicyu on 2016/12/1.
 */
public class VodCategoryUpdateRequest {
    /**
     *  用户分类id号
     */
    @JsonProperty("category_id")
    private Long categoryId;

    /**
     *  用户分类名称
     */
    @JsonProperty("category_name")
    private String categoryName;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
