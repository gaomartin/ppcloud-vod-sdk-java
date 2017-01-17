package com.pplive.ppcloud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by robertpicyu on 2016/12/1.
 */
public class VodCategoryAddRequest {
    /**
     *  用户分类名称
     */
    @JsonProperty("category_name")
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
