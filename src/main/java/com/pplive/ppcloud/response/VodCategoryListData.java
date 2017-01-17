package com.pplive.ppcloud.response;

/**
 * Created by robertpicyu on 2016/12/2.
 */
public class VodCategoryListData extends BaseResponse{
    private VodCategoryInfoResponse[] data;

    public VodCategoryInfoResponse[] getData() {
        return data;
    }

    public void setData(VodCategoryInfoResponse[] data) {
        this.data = data;
    }
}
