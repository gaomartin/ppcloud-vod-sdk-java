package com.pplive.ppcloud;

import com.pplive.ppcloud.vod.VodManager;
import com.pplive.ppcloud.request.*;
import com.pplive.ppcloud.response.*;
import com.pplive.ppcloud.utils.LogUtils;
import junit.framework.TestCase;

/**
 * Created by robertpicyu on 2016/12/6.
 */
public class APITest extends TestCase{
    private final String testCategoryName = "[VOD_SDK]-testCategory";
    private Long testCategoryId = 1192l;
    private String testChannelWebId = "0a2dnqyaqamkma6L4K2hoqrhoaSioaqaog";


    /**
     *
     * Method: addCategory(VodCategoryAddRequest request)
     *
     */
    public void testAddCategory() throws Exception {
        VodCategoryAddRequest vodCategoryAddRequest = new VodCategoryAddRequest();
        vodCategoryAddRequest.setCategoryName(testCategoryName);

        VodCategoryAddResponse vodCategoryAddResponse = VodManager.getInstance().addCategory(vodCategoryAddRequest);

        assertNotNull(vodCategoryAddResponse);
        assertEquals(vodCategoryAddResponse.getErr(),"0");
    }

    /**
     *
     * Method: getCategoryList(VodCategoryListRequset request)
     *
     */
    public void testGetCategoryList() throws Exception {
        VodCategoryListRequset request = new VodCategoryListRequset();
        request.setNeedCount(1);
        VodCategoryListData response = VodManager.getInstance().getCategoryList(request);

        assertNotNull(response);
        assertEquals(response.getErr(),"0");
        assertNotSame(response.getData().length, 0);
        LogUtils.log("get category " + response.getData().length);
    }

    /**
     *
     * Method: updateCategory(VodCategoryUpdateRequest request)
     *
     */
    public void testUpdateCategory() throws Exception {
        BaseResponse response = null;
        String expectedName = "[VOD_SDK]-testCategory-updated";
        VodCategoryUpdateRequest vodCategoryUpdateRequest = new VodCategoryUpdateRequest();
        vodCategoryUpdateRequest.setCategoryId(testCategoryId);
        vodCategoryUpdateRequest.setCategoryName(expectedName);

        response = VodManager.getInstance().updateCategory(vodCategoryUpdateRequest);

        assertNotNull(response);
        assertEquals(response.getErr(),"0");
    }

    /**
     *
     * Method: deleteCategory(VodCategoryDeleteRequest request)
     *
     */
    public void testDeleteCategory() throws Exception {
        BaseResponse response = null;
        VodCategoryDeleteRequest deleteRequest  = new VodCategoryDeleteRequest();
        deleteRequest.setCategoryId(testCategoryId);

        response = VodManager.getInstance().deleteCategory(deleteRequest);
        assertNotNull(response);
        assertEquals(response.getErr(),"0");
    }

    /**
     *
     * Method: getChannelList(VodChannelListRequest request)
     *
     */
    public void testGetChannelList() throws Exception {
        VodChannelListRequest request = new VodChannelListRequest();
        VodChannelListResponse response = null;
        //request.setCategoryId(testCategoryId);
        request.setPageNum(1);
        request.setPageSize(20);
        request.setChannelType("2");
        request.setSkipEncode(0);

        response = VodManager.getInstance().getChannelList(request);
        assertNotNull(response);
        assertEquals(response.getErr(),"0");
        LogUtils.log("get channel list  " + response.getData().length);
    }

    /**
     *
     * Method: createVod(String filePath, String fileName, Integer categoryId)
     *
     */
    public void testCreateVod() throws Exception {
        String filePath = "D:\\video\\out_video.mp4";
        VodChannelCreateRequest request = new VodChannelCreateRequest();
        request.setName("vodName");
        request.setSummary("my test vod name");
        VodManager.getInstance().createVod(filePath,request);
    }

    /**
     *
     * Method: getChannelInfo(VodChannalInfoRequest request)
     *
     */
    public void testGetChannelInfo() throws Exception {
        VodChannalInfoRequest request = new VodChannalInfoRequest();
        VodChannelInfoResponse response = null;
        request.setChannelWebId(testChannelWebId);

        response = VodManager.getInstance().getChannelInfo(request);
        assertNotNull(response);
        assertEquals(response.getErr(),"0");
    }


    /**
     *
     * Method: updateChannel(VodChannelUpdateRequest request)
     *
     */
    public void testUpdateChannel() throws Exception {
        VodChannelUpdateRequest request = new VodChannelUpdateRequest();

        VodChannelCommonResponse response = null;
        request.setChannelWebId(testChannelWebId);
        request.setCoverImg("http://grocery.ppqa.com/lpic/fef/20b/a1c/c47d805ab8286f8abdc1e0720be3c9bd[VOD-SDK-ADD].jpg");
        request.setChannelSummary("ffff");
        request.setChannelName("testffff");
        response = VodManager.getInstance().updateChannel(request);

        assertNotNull(response);
        assertEquals(response.getErr(),"0");
    }

    /**
     *
     * Method: setChannelPlayable(VodChannalPlayableRequest request)
     *
     */
    public void testSetChannelPlayable() throws Exception {
        VodChannalPlayableRequest request = new VodChannalPlayableRequest();
        VodChannelCommonResponse response = null;

        request.setChannelWebIds(testChannelWebId);
        request.setIsPlay(1);
        response = VodManager.getInstance().setChannelPlayable(request);

        assertNotNull(response);
        assertEquals(response.getErr(),"0");
    }

    /**
     *
     * Method: deleteChannel(VodChannelDeleteRequest request)
     *
     */
    public void testDeleteChannel() throws Exception {
        VodChannelDeleteRequest request = new VodChannelDeleteRequest();
        VodChannelCommonResponse response = null;

        request.setChannelWebIds(testChannelWebId);
        response = VodManager.getInstance().deleteChannel(request);

        assertNotNull(response);
        assertEquals(response.getErr(),"0");
    }
}
