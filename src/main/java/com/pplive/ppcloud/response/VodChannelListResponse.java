package com.pplive.ppcloud.response;

/**
 * Created by robertpicyu on 2016/12/5.
 */
public class VodChannelListResponse extends BaseResponse{
    private VodChannelInfoResponse[] data;

    private Integer totalnum;

    public Integer getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Integer totalnum) {
        this.totalnum = totalnum;
    }

    public VodChannelInfoResponse[] getData() {
        return data;
    }

    public void setData(VodChannelInfoResponse[] data) {
        this.data = data;
    }
}
