package com.pplive.ppcloud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by robertpicyu on 2016/12/2.
 */
public class VodCategoryDeleteRequest {
    /**
     *  用户分类id号
     */
    @JsonProperty("category_id")
    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
