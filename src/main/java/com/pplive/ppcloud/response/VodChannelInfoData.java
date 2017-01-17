package com.pplive.ppcloud.response;

/**
 * Created by robertpicyu on 2016/12/5.
 */
public class VodChannelInfoData extends BaseResponse{
    private VodChannelInfoResponse data;

    public VodChannelInfoResponse getData() {
        return data;
    }

    public void setData(VodChannelInfoResponse data) {
        this.data = data;
    }
}
