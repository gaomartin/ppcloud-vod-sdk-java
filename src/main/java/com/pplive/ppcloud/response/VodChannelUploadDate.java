package com.pplive.ppcloud.response;

/**
 * Created by robertpicyu on 2016/12/2.
 */
public class VodChannelUploadDate extends BaseResponse {
    private VodChannelUploadResponse data;

    public VodChannelUploadResponse getData() {
        return data;
    }

    public void setData(VodChannelUploadResponse data) {
        this.data = data;
    }
}
